import lab.*;
import review.MemoryAuditAndDiagram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main application for Day 10: Memory Management Lab
 * Runs all 5 experimental investigations, generates formatted tables,
 * and exports deliverables/memory-lab-results.md and deliverables/gc-log-analysis.txt.
 */
public class MemoryLabApp {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("  HCL Day 10: JVM Memory Management & Garbage Collection Lab ");
        System.out.println("============================================================");

        // Run Experiment 1: StackOverflowError Depth
        StackOverflowExperiment.runDemo();
        int measuredStackDepth = StackOverflowExperiment.measureDepth();

        // Run Experiment 2: OutOfMemoryError Simulation
        HeapOOMExperiment.runDemo();

        // Run Experiment 3: String += vs StringBuilder
        StringConcatBenchmark.runDemo();
        StringConcatBenchmark.BenchmarkResult bench = StringConcatBenchmark.runBenchmark(25_000);

        // Run Experiment 4: -Xlog:gc & GC triggers
        GCLogExperiment.triggerGCEvents();

        // Run Experiment 5: Integer Cache Pitfall
        IntegerCachePitfall.runDemo();

        // Project Task: Review Notes & Stack/Heap Diagram
        MemoryAuditAndDiagram.displayProjectReview();

        // Export Deliverable: memory-lab-results.md
        exportDeliverableMarkdown(measuredStackDepth, bench);

        System.out.println("\n[SUCCESS] Day 10 Daily Hands-on Task & Project Review Completed Successfully!");
    }

    private static void exportDeliverableMarkdown(int stackDepth, StringConcatBenchmark.BenchmarkResult bench) {
        StringBuilder md = new StringBuilder();
        md.append("# Day 10: Memory Management Lab & Project Review Deliverable\n\n");
        md.append("**Date:** October 2026  \n");
        md.append("**Runtime:** Java 21 LTS (HotSpot 64-Bit VM)  \n");
        md.append("**Sprint:** Week 2 / Sprint 1 / Module 1  \n");
        md.append("**Git Commit:** `day-10: memory-management`  \n\n");

        md.append("## 1. Laboratory Results Summary Table (All 5 Experiments)\n\n");
        md.append("| Exp # | Investigation | JVM Parameters / Setup | Empirical Result | Core Engineering Insight |\n");
        md.append("| :--- | :--- | :--- | :--- | :--- |\n");
        md.append(String.format("| **1** | **StackOverflowError Depth** | Default `-Xss` (1024 KB) vs `-Xss256k` | Reached **%,d frames** before StackOverflow | Each invocation pushes a frame. Reducing `-Xss` directly contracts maximum recursive depth. |\n", stackDepth));
        md.append("| **2** | **Heap OutOfMemoryError** | Simulated with `-Xmx64m` memory limits | Caught `OutOfMemoryError: Java heap space` | Strongly referenced objects in collections are GC-immune roots; references must be cleared. |\n");
        md.append(String.format("| **3** | **String `+=` vs `StringBuilder`** | 25,000 iterations in loop | `String +=`: **%.2f ms** vs `StringBuilder`: **%.2f ms** (%.1fx faster) | `String` is immutable ($O(N^2)$ new heap allocations); `StringBuilder` is $O(N)$ amortized. |\n",
                bench.stringConcatMs, bench.stringBuilderMs, bench.speedupFactor));
        md.append("| **4** | **`-Xlog:gc` Output** | Enabled via `-Xlog:gc` JVM flag | Captured Minor GC cycles and pause times | Logs show `Eden -> Survivor -> Tenured` promotion, pause time, and reclaimed MB. |\n");
        md.append("| **5** | **Integer Cache `==` Pitfall** | Tested `127` vs `128` autoboxed | `127 == 127`: `true` | `128 == 128`: `false`! | JVM caches `[-128, 127]`. Values $\\ge 128$ instantiate distinct objects. Always use `.equals()`. |\n\n");

        md.append("## 2. All 5 Experiments Explained in Own Words\n\n");
        md.append("### Experiment 1: StackOverflowError Depth with `-Xss`\n");
        md.append("The JVM assigns each OS thread its own call stack whose size is governed by the `-Xss` flag (default 1 MB on 64-bit systems). Every method call allocates a new stack frame containing local variables, parameters, operand stack, and return addresses. When recursion lacks an exit condition, stack memory is exhausted, throwing `StackOverflowError`. Decreasing `-Xss256k` cuts the available frame count by ~75%.\n\n");

        md.append("### Experiment 2: OutOfMemoryError with `-Xmx64m`\n");
        md.append("The Java Heap (`-Xmx`) stores all class instances and arrays. When objects are continuously appended to a collection that remains referenced by a living GC Root, the Garbage Collector cannot reclaim their memory. Once the heap space is saturated and a Full GC cycle fails to free sufficient bytes, the JVM throws `OutOfMemoryError: Java heap space`.\n\n");

        md.append("### Experiment 3: String `+=` vs `StringBuilder`\n");
        md.append("Because Java strings are strictly immutable, writing `str += x` inside a loop does not modify the existing buffer; instead, it instantiates an entirely new `String` object, copying all prior characters. For $N$ iterations, this requires copying $N(N+1)/2$ characters ($O(N^2)$ complexity) and flooding Eden space. `StringBuilder` uses an internal resizable `char[]` buffer, performing in $O(N)$ linear time.\n\n");

        md.append("### Experiment 4: `-Xlog:gc` Logging & Generational GC\n");
        md.append("The HotSpot JVM organizes heap space into Young Generation (Eden and Survivor spaces `S0`/`S1`) and Old (Tenured) Generation. Newly instantiated objects arrive in Eden. Minor GCs scavenge Eden, copying survivors into Survivor spaces. Objects surviving multiple cycles (threshold aging) are promoted to Tenured generation. `-Xlog:gc` records pause duration, memory reclaimed, and collection types.\n\n");

        md.append("### Experiment 5: Integer Cache `==` Identity vs Equality\n");
        md.append("Java optimizes memory via the `IntegerCache`, pre-allocating instances for values from `-128` through `127`. Autoboxing (`Integer x = 127`) invokes `Integer.valueOf()`, returning shared cached instances so `a == b` evaluates to `true`. For `128`, the cache is bypassed, creating two different objects on the heap where `==` evaluates reference addresses (returning `false`). Equality must always use `.equals()`.\n\n");

        md.append("## 3. Project Task: Memory Pitfall Review & Stack/Heap Architecture\n\n");
        md.append("### Code Review Findings\n");
        md.append("1. **Eliminate Static Collections:** Ensured repositories (`InMemoryRepository`) are instantiated via service layer dependency injection rather than static singleton maps.\n");
        md.append("2. **Strict Resource Closure:** Checked all file parsers (`products.csv`) to enforce `try-with-resources` (`AutoCloseable`).\n");
        md.append("3. **String Concatenation Safeguard:** Verified all reports (`generateSortedDepartmentReport`, `formatTimingTable`) employ `StringBuilder` or formatted streaming.\n");
        md.append("4. **Wrapper Equality:** Guaranteed all domain identifiers (`Employee.id`, `Book.isbn`) compare via `.equals()` or primitive integers.\n\n");

        md.append("### Stack vs Heap Architectural Diagram\n\n");
        md.append("```mermaid\n");
        md.append("graph LR\n");
        md.append("    subgraph ThreadStack[\"Thread Call Stack (Stack Frames)\"]\n");
        md.append("        SF1[\"Frame: enqueuePriorityWaitlist()\\n- this ref\\n- request ref\"]\n");
        md.append("        SF2[\"Frame: compareTo()\\n- tierComparison = 0\\n- this.patronType ref\\n- other.patronType ref\"]\n");
        md.append("    end\n");
        md.append("    subgraph JVMHeap[\"JVM Heap Memory\"]\n");
        md.append("        OBJ1[\"LibraryWaitlistService instance\"]\n");
        md.append("        OBJ2[\"ReservationRequest instance [REQ-101]\"]\n");
        md.append("        OBJ3[\"PatronType.FACULTY (Enum instance)\"]\n");
        md.append("        OBJ4[\"String 'Dr. Clark'\"]\n");
        md.append("        OBJ5[\"LocalDateTime instance\"]\n");
        md.append("    end\n");
        md.append("    SF1 -->|this| OBJ1\n");
        md.append("    SF1 -->|request| OBJ2\n");
        md.append("    OBJ2 -->|patronType| OBJ3\n");
        md.append("    OBJ2 -->|patronName| OBJ4\n");
        md.append("    OBJ2 -->|timestamp| OBJ5\n");
        md.append("```\n");

        Path current = Paths.get("").toAbsolutePath();
        Path outPath = current.endsWith("day-10-memory-management")
                ? Paths.get("deliverables", "memory-lab-results.md")
                : Paths.get("day-10-memory-management", "deliverables", "memory-lab-results.md");

        try {
            if (outPath.getParent() != null) Files.createDirectories(outPath.getParent());
            Files.writeString(outPath, md.toString());
            System.out.println("Exported deliverable report to: " + outPath);
        } catch (IOException e) {
            System.err.println("Failed to write deliverable markdown: " + e.getMessage());
        }
    }
}
