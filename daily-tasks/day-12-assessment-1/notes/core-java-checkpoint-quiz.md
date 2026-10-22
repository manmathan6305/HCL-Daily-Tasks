# 30-Minute Core Java Checkpoint Warm-up Quiz (Days 1–11)

### Q1: What is the exact difference between JVM, JRE, and JDK?
**Answer:** The JDK (Java Development Kit) contains the tools needed to write and compile Java code (e.g. `javac`, `javap`, debugger). The JRE (Java Runtime Environment) provides the standard libraries and JVM required to run compiled bytecode. The JVM (Java Virtual Machine) executes bytecode on the target OS, handling JIT compilation, memory management, and garbage collection.

### Q2: Why does `x == y` return false for two `Integer` instances with value 128?
**Answer:** Java caches `Integer` instances in the range `[-128, 127]` via `IntegerCache`. Values $\ge 128$ instantiate separate objects on the JVM heap. The `==` operator compares memory references rather than logical values. Objects must always be compared using `.equals()`.

### Q3: When should you use `ArrayDeque` instead of `LinkedList`?
**Answer:** In nearly all production queue and stack scenarios. `ArrayDeque` is backed by a circular array with contiguous memory layout and zero per-node allocation overhead. `LinkedList` allocates a 24–32 byte `Node` object for every element, resulting in frequent cache misses and heap fragmentation.

### Q4: Explain the PECS rule with an example.
**Answer:** PECS stands for **Producer Extends, Consumer Super**. Use `List<? extends T>` when reading elements from the list (the list *produces* data). Use `List<? super T>` when writing elements into the list (the list *consumes* data).

### Q5: What causes `ConcurrentModificationException` and how is it resolved?
**Answer:** It occurs when a collection is structurally modified during iteration through means other than the iterator's own methods. The collection's `modCount` diverges from the iterator's `expectedModCount`. The fix is to use `iterator.remove()` or concurrent collections (`ConcurrentHashMap`, `CopyOnWriteArrayList`).
