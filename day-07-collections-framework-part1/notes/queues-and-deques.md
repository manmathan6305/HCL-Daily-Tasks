# Queues and Deques in Java

## 1. Queue Interface
A `Queue` models a collection designed for holding elements prior to processing.
It exposes two forms of methods for each operation:

| Operation | Throws Exception on Failure | Returns Special Value (`false` or `null`) |
| :--- | :--- | :--- |
| **Insert** | `add(e)` | `offer(e)` |
| **Remove** | `remove()` | `poll()` |
| **Examine** | `element()` | `peek()` |

In production systems, `offer()`, `poll()`, and `peek()` are preferred to prevent unhandled runtime exceptions during queue saturation or emptiness.

---

## 2. ArrayDeque: High-Performance Deque
- Implemented as a resizable circular array with head and tail pointers.
- Does not permit `null` elements.
- Outperforms both `java.util.Stack` (for LIFO stack operations) and `LinkedList` (for FIFO queue operations).
- Zero pointer allocation overhead per enqueued element.

---

## 3. PriorityQueue: Heap-Based Ordering
- An unbounded priority queue backed by a balanced binary min-heap array.
- The head of the queue is the *least* element with respect to the specified ordering:
  - Natural ordering via `Comparable<T>`
  - Explicit custom ordering via `Comparator<T>`
- **Complexity:**
  - `offer()` / `add()`: $O(\log N)$
  - `poll()` / `remove()`: $O(\log N)$
  - `peek()` / `element()`: $O(1)$
- **Use Case:**
  - Task scheduling, triage ticketing, VIP/Faculty priority reservation systems.
