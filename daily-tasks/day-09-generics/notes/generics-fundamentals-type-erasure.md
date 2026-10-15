# Java Generics Fundamentals & Type Erasure

## 1. Why Generics?
Introduced in Java 5, Generics provide compile-time type safety and eliminate the need for casting.
- **Type Safety:** Catches `ClassCastException` at compile-time rather than runtime.
- **Elimination of Casts:** No manual downcasting required when reading from collections or containers.
- **Reusability:** Code algorithms and data structures once to operate on a wide variety of object types.

---

## 2. Generic Classes and Interfaces
```java
public class Pair<K, V> {
    private final K key;
    private final V value;
    // ...
}

public interface CrudRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
}
```

---

## 3. Type Erasure: How Generics Work Under the Hood
Java generics are implemented via **Type Erasure** for backward compatibility with pre-Java 5 bytecode:
1. **Erasure of Type Parameters:** The compiler replaces all type parameters with their first bound (or `Object` if unbounded).
   - `List<String>` becomes `List` (raw type).
   - `Box<T extends Number>` becomes `Box` where `T` is replaced with `Number`.
2. **Bridge Methods:** The compiler inserts synthetic bridge methods to preserve polymorphism in extended generic classes.
3. **Casting Insertion:** The compiler automatically inserts type casts in callers where type parameters were erased.

### Practical Implications of Type Erasure
- Cannot instantiate generic types directly: `new T()` is illegal.
- Cannot create generic arrays: `new T[10]` is illegal (must use `(T[]) new Object[10]` or `Array.newInstance`).
- Primitive types cannot be type arguments: `List<int>` is invalid; use wrapper `List<Integer>`.
- Cannot check types with `instanceof`: `if (obj instanceof List<String>)` is illegal due to erasure.
