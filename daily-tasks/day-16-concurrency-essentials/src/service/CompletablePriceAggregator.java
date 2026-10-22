package service;

import model.SupplierQuote;
import supplier.SlowSupplier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Asynchronous non-blocking aggregation using CompletableFuture:
 * - supplyAsync for parallel background dispatch
 * - exceptionally for graceful fallback when a supplier crashes (no thread death)
 * - CompletableFuture.allOf to orchestrate bulk completion
 * - thenApply / thenCombine for reactive transformation
 */
public class CompletablePriceAggregator {
    private final ExecutorService customExecutor;

    public CompletablePriceAggregator(int poolSize) {
        this.customExecutor = Executors.newFixedThreadPool(poolSize);
    }

    public List<SupplierQuote> fetchAllWithAllOf(List<SlowSupplier> suppliers, String productId) {
        // 1. Create a list of CompletableFutures with exceptionally fallback
        List<CompletableFuture<SupplierQuote>> futures = suppliers.stream()
                .map(supplier -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return supplier.fetchPrice(productId);
                    } catch (Exception e) {
                        throw new CompletionExceptionWrapper(e.getMessage());
                    }
                }, customExecutor).exceptionally(ex -> {
                    // Graceful fallback without crashing application
                    return SupplierQuote.failure(
                            supplier.getSupplierName(),
                            productId,
                            "Recovered via exceptionally fallback: " + ex.getMessage(),
                            0
                    );
                }))
                .collect(Collectors.toList());

        // 2. Combine all with CompletableFuture.allOf()
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        // 3. Extract completed results synchronously after all have settled
        return allOfFuture.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
        ).join();
    }

    public void shutdown() {
        customExecutor.shutdown();
        try {
            if (!customExecutor.awaitTermination(3, TimeUnit.SECONDS)) {
                customExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            customExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private static class CompletionExceptionWrapper extends RuntimeException {
        public CompletionExceptionWrapper(String message) {
            super(message);
        }
    }
}
