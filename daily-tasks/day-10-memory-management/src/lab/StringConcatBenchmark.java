package lab;

/**
 * Experiment 3: String += vs StringBuilder Timing Benchmark
 * Demonstrates the catastrophic quadratic O(N^2) heap allocation of repeated
 * String += concatenation in loops compared to O(N) StringBuilder.
 */
public class StringConcatBenchmark {

    public static class BenchmarkResult {
        public final int iterations;
        public final double stringConcatMs;
        public final double stringBuilderMs;
        public final double speedupFactor;

        public BenchmarkResult(int iterations, double stringConcatMs, double stringBuilderMs) {
            this.iterations = iterations;
            this.stringConcatMs = stringConcatMs;
            this.stringBuilderMs = stringBuilderMs;
            this.speedupFactor = stringBuilderMs > 0 ? (stringConcatMs / stringBuilderMs) : 0.0;
        }
    }

    public static BenchmarkResult runBenchmark(int iterations) {
        // 1. Benchmark String +=
        long start = System.nanoTime();
        String str = "";
        for (int i = 0; i < iterations; i++) {
            str += "x"; // Allocates a brand new String and char[] on every single iteration!
        }
        long stringConcatNs = System.nanoTime() - start;

        // 2. Benchmark StringBuilder
        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("x"); // Appends directly into contiguous resizable internal buffer
        }
        String result = sb.toString();
        long stringBuilderNs = System.nanoTime() - start;

        double concatMs = stringConcatNs / 1_000_000.0;
        double builderMs = stringBuilderNs / 1_000_000.0;

        return new BenchmarkResult(iterations, concatMs, builderMs);
    }

    public static void runDemo() {
        System.out.println("\n============================================================");
        System.out.println(" [EXP 3] STRING += VS STRINGBUILDER TIMING BENCHMARK");
        System.out.println("============================================================");

        int iterations = 25_000;
        System.out.printf("Running benchmark with %,d iterations...%n", iterations);
        BenchmarkResult result = runBenchmark(iterations);

        System.out.printf("   - String += Concat Time : %10.3f ms (O(N^2) allocation overhead)%n", result.stringConcatMs);
        System.out.printf("   - StringBuilder Time    : %10.3f ms (O(N) contiguous buffer)%n", result.stringBuilderMs);
        System.out.printf("   - Performance Gain      : %10.1fx FASTER with StringBuilder!%n", result.speedupFactor);
        System.out.println("Key Insight: String is immutable. Repeated '+=' allocates N strings of sizes");
        System.out.println("1, 2, ..., N copying N*(N+1)/2 characters, thrashing Young Gen memory!");
    }
}
