# Day 7: Collections Framework — Part 1

**Status:** Completed  
**Progress:** 100%  
**Week:** 2 | **Sprint:** 1 | **Module:** 1  
**Git Commit:** `day-7: collections-framework`

---

## Learning Objectives
By the end of this day, you will be able to:
- Navigate and select appropriate interfaces and concrete classes from the Java Collections Framework (JCF).
- Implement efficient collection-backed domain models using `ArrayList`, `LinkedList`, `LinkedHashSet`, `ArrayDeque`, and `PriorityQueue`.
- Prevent data duplicates in sequential pipelines using `LinkedHashSet`.
- Model dual-queue priority dispatch systems (FIFO waitlist vs Faculty-first VIP priority).
- Understand fail-fast iterators, identify the cause of `ConcurrentModificationException`, and perform safe mutations using `Iterator.remove()` and `ListIterator`.
- Empirically profile and benchmark collection performance (`ArrayList` vs `LinkedList`).

---

## Main Points Summary

| Concept | Description | Key Details |
| :--- | :--- | :--- |
| **`ArrayList` vs `LinkedList`** | Core List implementations | `ArrayList` offers $O(1)$ indexed reads and high cache locality. `LinkedList` offers $O(1)$ head insertion but suffers from high node memory overhead and cache misses. |
| **`LinkedHashSet`** | Insertion-ordered unique set | Combines a hash table with a doubly-linked list running through all entries. Ideal for de-duplicating incoming feeds without reordering. |
| **`ArrayDeque`** | Circular array double-ended queue | Faster than `Stack` and `LinkedList`. Provides $O(1)$ amortized `offerLast`, `pollFirst`, and `peek`. |
| **`PriorityQueue`** | Min-heap ordered queue | Serves elements according to natural `Comparable` priority or custom `Comparator`. Head element is always the highest priority item. |
| **`ConcurrentModificationException`**| Fail-fast iterator safeguard | Thrown when a collection is modified structurally during active iteration outside the iterator\'s own methods (`modCount != expectedModCount`). |
| **`Iterator.remove()` vs `ListIterator`**| Safe mutation mechanisms | `Iterator.remove()` updates `expectedModCount` synchronously. `ListIterator` supports bidirectional traversal (`hasPrevious`), element replacement (`set`), and insertion (`add`). |

---

## Estimated Study Time
Total: approximately 3 hours 30 minutes.

### Suggested Schedule
- **Session 1:** JCF Hierarchy, `List` vs `Set`, and `LinkedHashSet` de-duplication — 45 minutes.
- **Session 2:** Queue dynamics: `ArrayDeque` FIFO vs `PriorityQueue` — 60 minutes.
- **Session 3:** Iterator safety, `ConcurrentModificationException`, and `ListIterator` — 45 minutes.
- **Session 4:** Empirical benchmarking, timing table deliverable, and review — 60 minutes.

---

## Topics & Reference Notes

Detailed conceptual guides are provided in the `notes/` directory:
- [Collections Framework Overview](./notes/collections-overview.md)
- [List and Set Internals](./notes/list-vs-set-internals.md)
- [Queues and Deques](./notes/queues-and-deques.md)
- [Iterators and Concurrency Semantics](./notes/iterators-and-concurrency.md)

---

## Project Structure & Deliverables

```text
daily-tasks/day-07-collections-framework-part1/
├── deliverables/
│   └── timing-table.md             # Benchmark deliverable: ArrayList vs LinkedList
├── notes/
│   ├── collections-overview.md
│   ├── list-vs-set-internals.md
│   ├── queues-and-deques.md
│   └── iterators-and-concurrency.md
├── src/
│   ├── benchmark/
│   │   └── CollectionPerformanceBenchmark.java
│   ├── model/
│   │   ├── Book.java
│   │   ├── PatronType.java
│   │   └── ReservationRequest.java
│   ├── service/
│   │   └── LibraryWaitlistService.java
│   └── LibraryWaitlistApp.java     # Main driver implementing FR1-FR3
└── README.md
```

---

## Functional Requirements Implemented (FR1 - FR3)

- **FR1 (Catalogue & Deduplication):**
  - Manages catalogue entities using `ArrayList` for rapid access.
  - De-duplicates incoming shipments using `LinkedHashSet`, guaranteeing unique ISBNs while preserving original arrival sequence.
- **FR2 (Waitlist & Priority Reservation):**
  - Standard FIFO student waitlist backed by `ArrayDeque`.
  - Faculty-first priority waitlist backed by `PriorityQueue` where faculty members are automatically prioritized ahead of general requests.
- **FR3 (Overdue Item Auditing & Safe Iteration):**
  - Safely sweeps and purges overdue loans using `Iterator.remove()`.
  - Captures and explains `ConcurrentModificationException` on naive loops.
  - Demonstrates bidirectional inspection and modification using `ListIterator`.

---

## Setup and Run Instructions

1. Navigate to the `daily-tasks/day-07-collections-framework-part1` directory:
   ```bash
   cd daily-tasks/day-07-collections-framework-part1
   ```
2. Compile all source files:
   ```bash
   javac src/model/*.java src/service/*.java src/benchmark/*.java src/LibraryWaitlistApp.java
   ```
3. Run the application:
   ```bash
   java -cp src LibraryWaitlistApp
   ```

---

## Benchmark Deliverable Preview (`deliverables/timing-table.md`)

| Operation | Sample Size | ArrayList Time (ms) | LinkedList Time (ms) | Winner |
| :--- | :--- | :--- | :--- | :--- |
| **Insert at Head (`add(0, e)`)** | 30,000 | ~14.2 ms | ~1.8 ms | **LinkedList** (O(1) pointer rewire) |
| **Insert at Tail (`add(e)`)** | 30,000 | ~1.1 ms | ~2.9 ms | **ArrayList** (Contiguous cache locality) |
| **Insert at Middle (`add(mid, e)`)** | 10,000 | ~3.4 ms | ~8.7 ms | **ArrayList** (Block memory copy) |
| **Random Access (`get(i)`)** | 20,000 | ~0.08 ms | ~12.4 ms | **ArrayList** (Instant O(1) memory offset) |
