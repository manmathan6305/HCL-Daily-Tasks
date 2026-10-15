# Common Java Memory Pitfalls and Best Practices

## 1. Pitfall 1: Unbounded Growing Static Collections
```java
// ANTI-PATTERN: Leaks memory permanently!
public class MemoryLeakService {
    private static final List<Order> history = new ArrayList<>();
    public void record(Order o) { history.add(o); } // Never pruned!
}
```
**Fix:** Use bounded caches with LRU eviction policies (e.g. Caffeine Cache, Guava Cache) or clear entries when no longer needed.

---

## 2. Pitfall 2: Unclosed System & Network Resources
```java
// ANTI-PATTERN: File descriptor leak!
InputStream is = new FileInputStream("large.dat");
// If exception occurs here, stream remains open indefinitely
```
**Fix:** Always use `try-with-resources`:
```java
try (InputStream is = new FileInputStream("large.dat")) {
    // Process
}
```

---

## 3. Pitfall 3: String Concatenation in Loops
```java
// ANTI-PATTERN: O(N^2) memory allocations!
String s = "";
for (int i = 0; i < 10000; i++) {
    s += i; // Allocates 10,000 intermediate String objects!
}
```
**Fix:** Use `StringBuilder`:
```java
StringBuilder sb = new StringBuilder(10000);
for (int i = 0; i < 10000; i++) {
    sb.append(i);
}
```

---

## 4. Pitfall 4: Integer Cache `==` Trap
The JVM caches `Integer` objects between `-128` and `127` via `IntegerCache`:
```java
Integer x = 127;
Integer y = 127;
System.out.println(x == y); // true (points to cached singleton)

Integer a = 128;
Integer b = 128;
System.out.println(a == b); // FALSE! (Two distinct heap objects!)
```
**Fix:** Always use `.equals()` or primitive comparisons.
