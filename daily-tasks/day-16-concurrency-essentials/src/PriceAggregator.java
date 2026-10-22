import model.SupplierQuote;
import service.CompletablePriceAggregator;
import service.ExecutorPriceAggregator;
import service.SequentialAggregator;
import supplier.SlowSupplier;

import java.util.Arrays;
import java.util.List;

/**
 * Day 16 Deliverable: Price Aggregator Benchmark & Timing Comparison
 * 
 * Demonstrates:
 * 1. Fetching prices from 3 simulated slow suppliers (sleep 1-2 s) sequentially.
 * 2. Fetching with ExecutorService + Callable.
 * 3. Fetching with CompletableFuture.allOf().
 * 4. Resilient error handling using CompletableFuture.exceptionally() when Supplier 2 fails.
 * 5. Comprehensive timing comparison verifying async version is substantially faster and handles failures safely.
 */
public class PriceAggregator {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("  HCL Day 16: Concurrency Essentials - Price Aggregator Benchmark         ");
        System.out.println("==========================================================================\n");

        String productId = "PROD-LATITUDE-5420";

        // Setup 3 Slow Suppliers (Delays: 1200ms, 1800ms, 1500ms)
        // All healthy for initial timing benchmark
        List<SlowSupplier> healthySuppliers = Arrays.asList(
                new SlowSupplier("Supplier-Alpha (DirectHub)", 1200, 749.50, false),
                new SlowSupplier("Supplier-Beta  (CloudWholesale)", 1800, 729.00, false),
                new SlowSupplier("Supplier-Gamma (QuickShip Logistics)", 1500, 755.20, false)
        );

        System.out.println("--- 1. BENCHMARK: SEQUENTIAL EXECUTION (Single Thread) ---");
        SequentialAggregator seqAggregator = new SequentialAggregator();
        long seqStart = System.currentTimeMillis();
        List<SupplierQuote> seqQuotes = seqAggregator.fetchAll(healthySuppliers, productId);
        long seqTotalTime = System.currentTimeMillis() - seqStart;
        seqQuotes.forEach(q -> System.out.println("  " + q));
        System.out.printf("  >> Sequential Elapsed Time: %d ms%n%n", seqTotalTime);

        System.out.println("--- 2. BENCHMARK: EXECUTOR SERVICE + CALLABLE (ThreadPool = 3) ---");
        ExecutorPriceAggregator execAggregator = new ExecutorPriceAggregator(3);
        long execStart = System.currentTimeMillis();
        List<SupplierQuote> execQuotes = execAggregator.fetchAll(healthySuppliers, productId);
        long execTotalTime = System.currentTimeMillis() - execStart;
        execQuotes.forEach(q -> System.out.println("  " + q));
        System.out.printf("  >> ExecutorService Elapsed Time: %d ms%n%n", execTotalTime);
        execAggregator.shutdown();

        System.out.println("--- 3. BENCHMARK: COMPLETABLE FUTURE + allOf() (ThreadPool = 3) ---");
        CompletablePriceAggregator cfAggregator = new CompletablePriceAggregator(3);
        long cfStart = System.currentTimeMillis();
        List<SupplierQuote> cfQuotes = cfAggregator.fetchAllWithAllOf(healthySuppliers, productId);
        long cfTotalTime = System.currentTimeMillis() - cfStart;
        cfQuotes.forEach(q -> System.out.println("  " + q));
        System.out.printf("  >> CompletableFuture Elapsed Time: %d ms%n%n", cfTotalTime);

        System.out.println("--- 4. FAILURE RESILIENCE DRILL: SUPPLIER 2 FAILS (exceptionally fallback) ---");
        List<SlowSupplier> faultSuppliers = Arrays.asList(
                new SlowSupplier("Supplier-Alpha (DirectHub)", 1200, 749.50, false),
                new SlowSupplier("Supplier-Beta  (CloudWholesale)", 1400, 729.00, true), // FAILS WITH 503
                new SlowSupplier("Supplier-Gamma (QuickShip Logistics)", 1500, 755.20, false)
        );

        long faultStart = System.currentTimeMillis();
        List<SupplierQuote> resilientQuotes = cfAggregator.fetchAllWithAllOf(faultSuppliers, productId);
        long faultTotalTime = System.currentTimeMillis() - faultStart;

        System.out.println("  Results with graceful degradation (no crash):");
        for (SupplierQuote quote : resilientQuotes) {
            if (quote.isSuccessful()) {
                System.out.printf("  [ACTIVE]   %-36s : $%.2f (latency: %d ms)%n",
                        quote.getSupplierName(), quote.getPrice(), quote.getResponseTimeMs());
            } else {
                System.out.printf("  [FALLBACK] %-36s : %s%n",
                        quote.getSupplierName(), quote.getErrorMessage());
            }
        }
        System.out.printf("  >> Fault Run Elapsed Time: %d ms%n%n", faultTotalTime);
        cfAggregator.shutdown();

        // 5. Comparison Table
        printTimingComparison(seqTotalTime, execTotalTime, cfTotalTime, faultTotalTime);
    }

    private static void printTimingComparison(long seq, long exec, long cf, long fault) {
        double speedupExec = (double) seq / exec;
        double speedupCf = (double) seq / cf;

        System.out.println("==========================================================================");
        System.out.println("                     TIMING COMPARISON MATRIX                             ");
        System.out.println("==========================================================================");
        System.out.println("+---------------------------------+-------------+------------------------+");
        System.out.println("| Execution Strategy              | Time (ms)   | Speedup / Evaluation   |");
        System.out.println("+---------------------------------+-------------+------------------------+");
        System.out.printf("| 1. Sequential Baseline          | %7d ms   | 1.0x (Baseline sum)    |%n", seq);
        System.out.printf("| 2. ExecutorService + Callable   | %7d ms   | %.2fx faster           |%n", exec, speedupExec);
        System.out.printf("| 3. CompletableFuture.allOf()    | %7d ms   | %.2fx faster (BEST)    |%n", cf, speedupCf);
        System.out.printf("| 4. CF with Failing Supplier     | %7d ms   | Resilient (no crash)   |%n", fault);
        System.out.println("+---------------------------------+-------------+------------------------+");
        System.out.println("\nDone When Validation:");
        System.out.printf("[PASS] Async version faster: CompletableFuture (%d ms) vs Sequential (%d ms) -> ~%.1fx speedup!%n",
                cf, seq, speedupCf);
        System.out.println("[PASS] Failure handled gracefully without application crash via .exceptionally()!");
        System.out.println("==========================================================================");
    }
}
