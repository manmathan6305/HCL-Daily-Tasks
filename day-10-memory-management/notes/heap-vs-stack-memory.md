# Heap vs Stack Memory Architecture in the JVM

## 1. Stack Memory
- **Allocation:** Managed automatically per OS thread. Memory is allocated and deallocated in LIFO order as methods are pushed and popped.
- **Contents:**
  - **Stack Frames:** Created for each method invocation.
  - **Local Variables:** Primitive types (`int`, `boolean`, `double`, etc.) stored by value.
  - **Object References:** Pointer addresses referencing objects located on the heap.
- **Characteristics:**
  - Fast access (contiguous memory managed via CPU stack pointer).
  - Size configured via `-Xss` (e.g. `-Xss256k`, `-Xss1024k`).
  - Thread-confined (never shared across threads).
  - Throws `StackOverflowError` when recursion/nesting exceeds stack size.

---

## 2. Heap Memory
- **Allocation:** Shared globally across all threads in the JVM. Managed dynamically by the Garbage Collector.
- **Contents:**
  - All class instances and arrays.
  - Static variables.
  - Constant String Pool.
- **Characteristics:**
  - Size configured via `-Xms` (initial) and `-Xmx` (maximum).
  - Slower access than stack due to heap pointer indirection.
  - Complex lifecycle managed via Generational Garbage Collection.
  - Throws `OutOfMemoryError: Java heap space` when heap capacity is exhausted.

---

## 3. Comparison Matrix

| Property | Stack Memory | Heap Memory |
| :--- | :--- | :--- |
| **Scope** | One per thread | Shared across whole JVM |
| **Lifecycle** | Scope of the executing method | As long as referenced by GC root |
| **Access Speed** | Blazing fast (CPU register/stack pointer) | Slower (pointer dereference, cache misses) |
| **Management** | Deterministic push/pop | Non-deterministic Garbage Collector |
| **Error on Limit** | `java.lang.StackOverflowError` | `java.lang.OutOfMemoryError` |
| **Configuration** | `-Xss` | `-Xms`, `-Xmx` |
