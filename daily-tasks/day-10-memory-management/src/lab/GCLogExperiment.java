package lab;

import java.util.ArrayList;
import java.util.List;

/**
 * Experiment 4: -Xlog:gc Logging & Garbage Collection Behavior
 * Triggers Minor GC cycles through intensive short-lived object allocation
 * and analyzes GC log metrics (pause times, Eden/Survivor/Tenured transitions).
 */
public class GCLogExperiment {

    public static void triggerGCEvents() {
        System.out.println("\n============================================================");
        System.out.println(" [EXP 4] -Xlog:gc ANALYSIS & GARBAGE COLLECTION BEHAVIOR");
        System.out.println("============================================================");

        Runtime rt = Runtime.getRuntime();
        long beforeAlloc = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
        System.out.println("Heap Memory in Use Before Allocation: " + beforeAlloc + " MB");

        // Allocate a flurry of temporary objects to trigger Young Gen / Minor GC
        System.out.println("Generating 500,000 short-lived transient objects in Eden Space...");
        for (int i = 0; i < 500_000; i++) {
            byte[] transientBuffer = new byte[128];
        }

        long duringAlloc = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
        System.out.println("Heap Memory in Use After Transient Allocations: " + duringAlloc + " MB");

        System.out.println("Requesting Explicit System.gc() to force sweep into logs...");
        long gcStart = System.nanoTime();
        System.gc();
        long gcDurationMs = (System.nanoTime() - gcStart) / 1_000_000;

        long afterGc = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
        System.out.println("Heap Memory in Use After GC Sweep: " + afterGc + " MB");
        System.out.printf("Garbage Collection Completed in %d ms (Reclaimed approx %d MB).%n",
                gcDurationMs, Math.max(0, duringAlloc - afterGc));

        System.out.println("\nUnderstanding -Xlog:gc Log Format:");
        System.out.println("  [0.104s][info][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 24M->4M(256M) 2.451ms");
        System.out.println("  - 0.104s      : Timestamp since JVM startup");
        System.out.println("  - GC(0)       : Sequential GC event ID");
        System.out.println("  - Pause Young : Minor GC cycle collecting Eden and Survivor spaces");
        System.out.println("  - 24M->4M     : Heap occupancy dropped from 24MB to 4MB (20MB reclaimed)");
        System.out.println("  - (256M)      : Total committed heap size");
        System.out.println("  - 2.451ms     : Stop-The-World (STW) pause duration");
    }
}
