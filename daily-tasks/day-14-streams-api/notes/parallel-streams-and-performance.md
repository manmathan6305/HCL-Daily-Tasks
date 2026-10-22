# Parallel Streams: When They Help and When They Hurt

## 1. How Parallel Streams Work
Parallel streams partition the source collection using a `Spliterator` and distribute chunks across worker threads in the JVM's `ForkJoinPool.commonPool()`.

## 2. The N * Q Rule
- $N$ = Number of elements in the collection.
- $Q$ = Cost per element computation.
- If $N \times Q > 10,000$, parallelization typically yields noticeable speedup.

---

## 3. When Parallel Streams HELP
1. **CPU-Intensive Tasks:** Cryptographic hashing, mathematical simulations, image processing per element.
2. **ArrayList / Array Sources:** Fast $O(1)$ random-access index splitting with zero pointer chasing.
3. **Stateless Operations:** `filter()`, `map()` where threads run completely independent of one another.

---

## 4. When Parallel Streams HURT
1. **I/O Bound Work:** Blocking database queries or network HTTP calls starve the shared `ForkJoinPool`.
2. **Linked Collections (`LinkedList`):** Splitting requires traversing pointers ($O(N)$ split overhead).
3. **Stateful Operations (`sorted()`, `distinct()`):** Require inter-thread synchronization and merge phases.
4. **Boxing / Unboxing Overhead:** Boxing primitives introduces memory churn and cache misses.
