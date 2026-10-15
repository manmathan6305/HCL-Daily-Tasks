# The hashCode() and equals() Contract in Java

## 1. The Official Contract Specification
Java's `Object.equals()` and `Object.hashCode()` form a strict contractual relationship:
1. **Consistency:** If `x.equals(y)` is true, then `x.hashCode() == y.hashCode()` **MUST** always be true.
2. **Reflexive, Symmetric, Transitive:**
   - `x.equals(x)` is true.
   - `x.equals(y)` == `y.equals(x)`.
   - If `x.equals(y)` and `y.equals(z)`, then `x.equals(z)`.
3. **Unequal Objects:** If `!x.equals(y)`, their hashCodes *may* be identical (a hash collision), but distinct hashCodes improve hash table distribution and performance.

---

## 2. The Missing-hashCode Bug

### The Problem
If a developer overrides `equals()` to compare logical fields (e.g., employee `id`), but forgets to override `hashCode()`:
- `key1` and `key2` have identical logical attributes: `key1.equals(key2) == true`.
- However, `key1.hashCode()` and `key2.hashCode()` inherit default `Object.hashCode()`, which derives from the memory address of the object in JVM heap.
- `key1.hashCode() != key2.hashCode()`.

### The Catastrophic Result in HashMap / HashSet
```java
Map<MyKey, String> map = new HashMap<>();
map.put(new MyKey(101), "Alice");

// Attempt to read:
String value = map.get(new MyKey(101)); // Returns NULL!
```
1. `map.get(new MyKey(101))` calculates the hash of the new instance.
2. It looks up the bucket at index `(n - 1) & hash`.
3. Because the hash is completely different from the original key's hash, HashMap inspects an empty bucket and immediately returns `null`.
4. The map will **never** invoke `.equals()`, rendering the data unretrievable!

### The Safe Fix
Always use `java.util.Objects`:
```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Employee e)) return false;
    return id == e.id && Objects.equals(name, e.name);
}

@Override
public int hashCode() {
    return Objects.hash(id, name);
}
```
