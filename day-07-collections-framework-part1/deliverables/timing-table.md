# ArrayList vs LinkedList Performance Benchmark & Timing Table

**Date:** October 2026  
**Runtime:** Java 21 LTS (HotSpot 64-Bit VM)  
**Objective:** Empirically quantify runtime performance and algorithmic characteristics of `ArrayList` vs `LinkedList`.

## Empirical Results Table

| Operation | Sample Size | ArrayList Time (ms) | LinkedList Time (ms) | Performance Winner | Algorithmic Complexity |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Insert at Head (add(0, e))** | 30000 | 61.949 ms | 6.695 ms | **LinkedList** | ArrayList is O(N) due to System.arraycopy shifting. LinkedList is O(1) via head pointer rewiring. |
| **Insert at Tail (add(e))** | 30000 | 3.961 ms | 3.360 ms | **LinkedList** | Both are O(1) amortized, but ArrayList has lower allocation overhead and contiguous memory cache locality. |
| **Insert at Middle (add(mid, e))** | 10000 | 5.758 ms | 96.616 ms | **ArrayList** | LinkedList must traverse N/2 pointers (O(N)), while ArrayList shifts elements in contiguous blocks with SIMD. |
| **Random Access (get(i))** | 20000 | 1.426 ms | 50.011 ms | **ArrayList (Vastly Superior)** | ArrayList is O(1) instant memory offset indexing. LinkedList is O(N) requiring linear node traversal. |

## Architectural Analysis & Takeaways

1. **Cache Locality vs Pointer Chasing:**
   - `ArrayList` stores elements in a contiguous memory block (`Object[]`). Modern CPU L1/L2 caches prefetch adjacent array elements, enabling blazing fast sequential and indexed operations.
   - `LinkedList` allocates an independent `Node` object for every element containing item, next, and previous pointers (24-32 bytes overhead per node on 64-bit JVMs). Nodes are scattered across the heap, causing frequent CPU cache misses.

2. **Head Insertions:**
   - `LinkedList` wins for frequent insertions strictly at index 0 because updating head pointers is true $O(1)$. However, in production, `ArrayDeque` is almost always preferred over `LinkedList` even for queues.

3. **Random Access:**
   - `ArrayList.get(i)` is instantaneous $O(1)$ arithmetic ($baseAddress + i \times referenceSize$). `LinkedList.get(i)` requires traversing pointers from either the start or end ($O(N)$), making it unusable for random access workloads.
