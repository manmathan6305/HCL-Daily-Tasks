# Day 10: Memory Management & Garbage Collection

**Status:** Completed  
**Progress:** 100%  
**Week:** 2 | **Sprint:** 1 | **Module:** 1  
**Deliverables:** `deliverables/memory-lab-results.md` (Lab Table + Project Review Notes + Stack/Heap Diagram)  
**Git Commit:** `day-10: memory-management`  

---

## Learning Objectives
By the end of this day, you will be able to:
- Contrast the physical and operational differences between JVM **Stack Memory** and **Heap Memory**.
- Trace method execution frames, primitive values, and heap object references.
- Measure and understand thread stack limits (`-Xss`) and `StackOverflowError`.
- Diagnose heap exhaustion (`-Xmx`) and manage `OutOfMemoryError`.
- Empirically quantify the performance penalty of loop-based `String +=` concatenation versus `StringBuilder`.
- Interpret `-Xlog:gc` Garbage Collection logging output and generational transitions (`Eden -> Survivor -> Tenured`).
- Identify and prevent the classic **Integer Cache `==` Pitfall** (`[-128, 127]`).
- Perform code audits for the 4 primary Java memory pitfalls.

---

## Main Points Summary

| Concept | Description | Key Details |
| :--- | :--- | :--- |
| **Heap vs Stack** | Primary memory areas | Stack holds thread execution frames and local variables ($O(1)$ LIFO push/pop). Heap stores all shared objects managed by the GC. |
| **Stack Overflow** | Thread stack exhaustion | Thrown when call stack frames exceed `-Xss` limit (typically due to unbounded recursion). |
| **Out of Memory** | Heap allocation exhaustion | Occurs when active GC Roots retain unreachable/unbounded objects, exceeding `-Xmx`. |
| **String Concatenation** | Immutability penalty | `String +=` inside loops exhibits $O(N^2)$ allocation complexity; `StringBuilder` operates in $O(N)$ amortized time. |
| **Generational GC** | Partitioned heap management | Segregates heap into Young (Eden, S0, S1) and Old (Tenured) spaces based on the weak generational hypothesis. |
| **`-Xlog:gc`** | JVM unified GC logging | Logs GC events, Stop-The-World (STW) pauses, memory occupancy changes, and reclamation metrics. |
| **Integer Cache** | Flyweight optimization | Pre-caches `Integer` objects in range `[-128, 127]`. Comparisons using `==` fail outside this range; `.equals()` is required. |

---

## Estimated Study Time
Total: approximately 3 hours 30 minutes.

### Suggested Schedule
- **Session 1:** Heap vs Stack architecture & StackOverflowError with `-Xss` — 45 minutes.
- **Session 2:** OutOfMemoryError, Heap tuning (`-Xmx`), and `-Xlog:gc` interpretation — 60 minutes.
- **Session 3:** String concatenation performance & Integer Cache pitfall — 45 minutes.
- **Session 4:** Code review for memory pitfalls and Stack/Heap diagram deliverable — 60 minutes.

---

## Topics & Reference Notes

Detailed conceptual guides are provided in the `notes/` directory:
- [Heap vs Stack Memory Architecture](./notes/heap-vs-stack-memory.md)
- [Garbage Collection Basics & Tuning Flags](./notes/garbage-collection-tuning.md)
- [Common Memory Pitfalls and Best Practices](./notes/common-memory-pitfalls.md)

---

## Project Structure & Deliverables

```text
day-10-memory-management/
├── deliverables/
│   └── memory-lab-results.md       # Lab Table + Project Review Notes + Diagram
├── notes/
│   ├── common-memory-pitfalls.md
│   ├── garbage-collection-tuning.md
│   └── heap-vs-stack-memory.md
├── src/
│   ├── lab/
│   │   ├── GCLogExperiment.java
│   │   ├── HeapOOMExperiment.java
│   │   ├── IntegerCachePitfall.java
│   │   ├── StackOverflowExperiment.java
│   │   └── StringConcatBenchmark.java
│   ├── review/
│   │   └── MemoryAuditAndDiagram.java
│   └── MemoryLabApp.java           # Main runner executing all 5 experiments
└── README.md
```

---

## Setup and Run Instructions

1. Navigate to the `day-10-memory-management` directory:
   ```bash
   cd day-10-memory-management
   ```
2. Compile all source files:
   ```bash
   javac src/lab/*.java src/review/*.java src/MemoryLabApp.java
   ```
3. Run the application:
   ```bash
   java -cp src MemoryLabApp
   ```
4. Run with enabled GC logging to observe output:
   ```bash
   java -Xlog:gc -cp src MemoryLabApp
   ```
