import generator.SalesOrderGenerator;
import model.Order;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DELIVERABLE: SalesAnalytics.java
 *
 * Implements Streams API requirements:
 * 1. Revenue by Category (groupingBy + summingDouble)
 * 2. Top 3 Customers by total expenditure
 * 3. Orders Partitioned by Delivery Status (partitioningBy)
 * 4. Average Basket Size (averagingDouble & DoubleSummaryStatistics)
 * 5. Distinct comma-joined product list (map + distinct + joining)
 * 6. Sequential vs Parallel Stream micro-benchmark for CPU-heavy map
 *
 * Rule Compliance: All reports use Collectors; ZERO external mutation in streams!
 */
public class SalesAnalytics {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("  HCL Day 14: Enterprise Sales Analytics & Streams API      ");
        System.out.println("============================================================");

        SalesAnalytics analytics = new SalesAnalytics();
        analytics.runAnalyticsPipeline();
    }

    public void runAnalyticsPipeline() {
        // Generate dataset of exactly 1,000 orders
        List<Order> orders = SalesOrderGenerator.generateOrders(1000);
        System.out.printf("Loaded %,d orders for analytics processing.%n", orders.size());

        // 1. Revenue by Category (groupingBy + summingDouble)
        System.out.println("\n------------------------------------------------------------");
        System.out.println(" [QUERY 1] REVENUE BY CATEGORY (groupingBy + summingDouble) ");
        System.out.println("------------------------------------------------------------");
        Map<String, Double> revenueByCategory = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCategory,
                        Collectors.summingDouble(Order::getTotalAmount)
                ));

        revenueByCategory.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(e -> System.out.printf("  * %-20s : $%,12.2f%n", e.getKey(), e.getValue()));

        // 2. Top 3 Customers by Total Expenditure
        System.out.println("\n------------------------------------------------------------");
        System.out.println(" [QUERY 2] TOP 3 CUSTOMERS BY SPEND (groupingBy + limit)   ");
        System.out.println("------------------------------------------------------------");
        Map<String, Double> spendByCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.summingDouble(Order::getTotalAmount)
                ));

        List<Map.Entry<String, Double>> top3Customers = spendByCustomer.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());

        int rank = 1;
        for (Map.Entry<String, Double> entry : top3Customers) {
            System.out.printf("  #%d -> %-10s : Total Spend = $%,11.2f%n", rank++, entry.getKey(), entry.getValue());
        }

        // 3. Orders Partitioned by Status (Delivered vs Non-Delivered)
        System.out.println("\n------------------------------------------------------------");
        System.out.println(" [QUERY 3] ORDERS PARTITIONED BY STATUS (partitioningBy)    ");
        System.out.println("------------------------------------------------------------");
        Map<Boolean, List<Order>> partitionedOrders = orders.stream()
                .collect(Collectors.partitioningBy(Order::isDelivered));

        int deliveredCount = partitionedOrders.get(true).size();
        int pendingCancelledCount = partitionedOrders.get(false).size();
        double deliveredRevenue = partitionedOrders.get(true).stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();

        System.out.printf("  - Delivered Orders      : %d orders | Value = $%,12.2f%n", deliveredCount, deliveredRevenue);
        System.out.printf("  - Pending/Cancelled     : %d orders%n", pendingCancelledCount);

        // 4. Average Basket Size & Statistical Summary
        System.out.println("\n------------------------------------------------------------");
        System.out.println(" [QUERY 4] BASKET SIZE SUMMARY (DoubleSummaryStatistics)    ");
        System.out.println("------------------------------------------------------------");
        DoubleSummaryStatistics stats = orders.stream()
                .collect(Collectors.summarizingDouble(Order::getTotalAmount));

        System.out.printf("  - Total Transaction Volume : $%,12.2f%n", stats.getSum());
        System.out.printf("  - Average Basket Size      : $%,12.2f%n", stats.getAverage());
        System.out.printf("  - Minimum Order Value      : $%,12.2f%n", stats.getMin());
        System.out.printf("  - Maximum Order Value      : $%,12.2f%n", stats.getMax());

        // 5. Distinct Comma-Joined Product List
        System.out.println("\n------------------------------------------------------------");
        System.out.println(" [QUERY 5] DISTINCT PRODUCT CATALOG (map + distinct + joining)");
        System.out.println("------------------------------------------------------------");
        String catalogString = orders.stream()
                .map(Order::getProductName)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.println("Distinct Products Catalog (" + catalogString.split(", ").length + " products):");
        System.out.println("  " + catalogString);

        // 6. Sequential vs Parallel Performance Benchmark (CPU-heavy map)
        System.out.println("\n------------------------------------------------------------");
        System.out.println(" [BENCHMARK] SEQUENTIAL VS PARALLEL STREAM (CPU-HEAVY MAP)  ");
        System.out.println("------------------------------------------------------------");
        runParallelBenchmark(orders);

        System.out.println("\n[SUCCESS] Day 14 Streams API & SalesAnalytics Completed Successfully!");
    }

    private void runParallelBenchmark(List<Order> orders) {
        // Warmup JVM
        for (int i = 0; i < 50; i++) computeHeavyTaxHash(orders.get(i));

        // 1. Sequential Stream
        long startSeq = System.nanoTime();
        List<Double> seqResults = orders.stream()
                .map(this::computeHeavyTaxHash)
                .collect(Collectors.toList());
        long seqDurationMs = (System.nanoTime() - startSeq) / 1_000_000;

        // 2. Parallel Stream
        long startPar = System.nanoTime();
        List<Double> parResults = orders.parallelStream()
                .map(this::computeHeavyTaxHash)
                .collect(Collectors.toList());
        long parDurationMs = (System.nanoTime() - startPar) / 1_000_000;

        double speedup = (parDurationMs > 0) ? ((double) seqDurationMs / parDurationMs) : 1.0;

        System.out.printf("  - Sequential Stream Time : %4d ms%n", seqDurationMs);
        System.out.printf("  - Parallel Stream Time   : %4d ms%n", parDurationMs);
        System.out.printf("  - Parallel Speedup Factor: %.2fx FASTER on multi-core CPU!%n", speedup);
        System.out.println("  Takeaway: For CPU-intensive operations on large collections, parallelStream()");
        System.out.println("  effectively leverages the ForkJoinPool.commonPool across available cores.");
    }

    /**
     * Simulates a CPU-heavy financial computation (e.g. Monte-Carlo tax simulation & trigonometric hashing).
     */
    private double computeHeavyTaxHash(Order order) {
        double result = order.getTotalAmount();
        for (int i = 0; i < 20_000; i++) {
            result = Math.sin(result) * Math.cos(result) + Math.sqrt(Math.abs(result) + 1.0);
        }
        return result;
    }
}
