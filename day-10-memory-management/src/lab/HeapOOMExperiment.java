package lab;

import java.util.ArrayList;
import java.util.List;

/**
 * Experiment 2: OutOfMemoryError Simulation (-Xmx Heap Exhaustion)
 * Demonstrates heap allocation limits, object retention in static/strong references,
 * and graceful catch/recovery from OutOfMemoryError.
 */
public class HeapOOMExperiment {

    public static class MemoryPayload {
        private final byte[] data = new byte[1024 * 1024]; // 1 MB payload
        private final int id;

        public MemoryPayload(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public static String runSimulation() {
        List<MemoryPayload> leakedList = new ArrayList<>();
        Runtime runtime = Runtime.getRuntime();
        long initialFree = runtime.freeMemory() / (1024 * 1024);
        long maxHeap = runtime.maxMemory() / (1024 * 1024);

        int count = 0;
        try {
            // Allocate 1MB chunks continuously until heap limit is reached
            while (count < 2000) {
                leakedList.add(new MemoryPayload(count++));
            }
        } catch (OutOfMemoryError e) {
            // Free references immediately so JVM can recover and report
            int allocatedMB = count;
            leakedList = null; // Mark for GC collection
            System.gc(); // Hint GC to reclaim memory
            return String.format("Caught OutOfMemoryError: Java heap space after allocating %d MB (Max Heap: %d MB, Initial Free: %d MB)",
                    allocatedMB, maxHeap, initialFree);
        }
        return "Simulation concluded without OOM (Allocated: " + count + " MB)";
    }

    public static void runDemo() {
        System.out.println("\n============================================================");
        System.out.println(" [EXP 2] OUTOFMEMORYERROR HEAP EXHAUSTION (-Xmx LIMITS)");
        System.out.println("============================================================");
        String result = runSimulation();
        System.out.println("Result: " + result);
        System.out.println("Key Insight: Heap objects remain unreachable only when all root references");
        System.out.println("are severed. Unbounded collection growth prevents Garbage Collection.");
    }
}
