# Iterators, ListIterator, and Concurrency Semantics

## 1. What is an Iterator?
The `Iterator<E>` interface enables sequential traversal over any `Collection`:
```java
Iterator<Book> it = catalogue.iterator();
while (it.hasNext()) {
    Book b = it.next();
    if (b.isOverdue()) {
        it.remove(); // The ONLY safe way to remove during iteration
    }
}
```

## 2. Iterator vs ListIterator

| Feature | `Iterator` | `ListIterator` |
| :--- | :--- | :--- |
| **Direction** | Forward only | Bidirectional (Forward & Backward) |
| **Index Inquiry** | No | `nextIndex()`, `previousIndex()` |
| **Modifications Allowed** | `remove()` only | `remove()`, `set(e)`, `add(e)` |
| **Applicable To** | Any `Collection` | `List` only |

---

## 3. The ConcurrentModificationException Explained
Java collections implement **fail-fast** iterators using an internal modification counter:
```java
protected transient int modCount = 0;
```
When an iterator is constructed, it snapshots this counter:
```java
int expectedModCount = modCount;
```

### Why Naive Removal Fails:
```java
for (Book b : catalogue) {
    if (b.isOverdue()) {
        catalogue.remove(b); // modCount is incremented!
    }
}
```
During the next call to `iterator.next()`, the iterator verifies:
```java
if (modCount != expectedModCount)
    throw new ConcurrentModificationException();
```
Because the collection was altered directly rather than through the iterator, `modCount` diverges from `expectedModCount`, immediately throwing `ConcurrentModificationException` to prevent subtle corruption.

### How `Iterator.remove()` Fixes It:
When `it.remove()` is invoked, it updates the underlying collection AND updates `expectedModCount = modCount`, keeping both in complete synchronization!
