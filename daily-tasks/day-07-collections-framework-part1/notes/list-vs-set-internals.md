# List and Set Implementations: Internals & Performance

## 1. ArrayList vs LinkedList

### ArrayList Internals
- Backed by a resizable `Object[]` array.
- Default initial capacity is 10.
- When capacity is exceeded, it grows by $50\%$ ($newCapacity = oldCapacity + (oldCapacity >> 1)$) using `Arrays.copyOf()`.
- **Pros:**
  - $O(1)$ constant time for `get(i)` and `set(i, e)` due to direct memory offset calculation.
  - Excellent CPU cache locality (elements reside in contiguous cache lines).
  - Minimal memory overhead per element (only the object reference pointer, 4 or 8 bytes).
- **Cons:**
  - $O(N)$ for insertions and deletions at arbitrary positions because elements must be shifted via `System.arraycopy()`.

### LinkedList Internals
- Doubly-linked list composed of private static `Node<E>` objects:
  ```java
  private static class Node<E> {
      E item;
      Node<E> next;
      Node<E> prev;
  }
  ```
- **Pros:**
  - $O(1)$ for inserting or deleting at the head or tail once the reference is acquired.
- **Cons:**
  - $O(N)$ for random access (`get(i)` must traverse half the list from the closest end).
  - High memory footprint: Each element wraps inside a `Node` object having 2 pointers + object header (24-32 bytes overhead per element).
  - Terrible cache locality due to scattered heap addresses.

---

## 2. Set Implementations: HashSet vs LinkedHashSet vs TreeSet

| Feature | `HashSet` | `LinkedHashSet` | `TreeSet` |
| :--- | :--- | :--- | :--- |
| **Underlying Data Structure** | `HashMap` (elements are keys) | `LinkedHashMap` (doubly-linked hash buckets) | `TreeMap` (Red-Black Tree) |
| **Order Guarantee** | None (Unpredictable hash bucket order) | Preserves Insertion Order | Natural or `Comparator` Sorted Order |
| **Lookup Time Complexity** | $O(1)$ average | $O(1)$ average | $O(\log N)$ |
| **Null Elements Allowed?** | Yes (one null key) | Yes (one null key) | No (if using natural ordering) |
| **Use Case** | Fast uniqueness check | De-duplicating streams while retaining sequence | Sorted distinct sets, range queries |

### LinkedHashSet De-Duplication Pattern
```java
// Eliminates duplicates while retaining original encounter order
List<Book> rawList = getIncomingShipment();
Set<Book> deduplicated = new LinkedHashSet<>(rawList);
List<Book> uniqueList = new ArrayList<>(deduplicated);
```
