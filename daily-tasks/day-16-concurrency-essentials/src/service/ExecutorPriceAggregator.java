package service;

import model.SupplierQuote;
import supplier.SlowSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Concurrency via ExecutorService with Callable tasks.
 * Uses Future.get() to harvest results (blocking per future).
 */
public class ExecutorPriceAggregator {
    private final ExecutorService executor;

    public ExecutorPriceAggregator(int threadPoolSize) {
        this.executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    public List<SupplierQuote> fetchAll(List<SlowSupplier> suppliers, String productId) {
        List<Future<SupplierQuote>> futures = new ArrayList<>();

        for (SlowSupplier supplier : suppliers) {
            Callable<SupplierQuote> task = () -> {
                long start = System.currentTimeMillis();
                try {
                    return supplier.fetchPrice(productId);
                } catch (Exception e) {
                    long elapsed = System.currentTimeMillis() - start;
                    return SupplierQuote.failure(supplier.getSupplierName(), productId, e.getMessage(), elapsed);
                }
            };
            futures.add(executor.submit(task));
        }

        List<SupplierQuote> results = new ArrayList<>();
        for (Future<SupplierQuote> future : futures) {
            try {
                results.add(future.get()); // Blocking wait for each worker
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                results.add(SupplierQuote.failure("UNKNOWN", productId, "Thread interrupted: " + e.getMessage(), 0));
            } catch (ExecutionException e) {
                results.add(SupplierQuote.failure("UNKNOWN", productId, "Execution failure: " + e.getCause().getMessage(), 0));
            }
        }
        return results;
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
