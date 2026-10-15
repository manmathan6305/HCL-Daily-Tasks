# Try-With-Resources and the AutoCloseable Interface

## 1. What is Try-With-Resources?
Introduced in Java 7, the `try-with-resources` statement guarantees that each resource declared in parentheses is closed at the end of the statement, regardless of whether exceptions were thrown:

```java
try (BufferedReader reader = Files.newBufferedReader(path)) {
    return reader.readLine();
} // Automatically invokes reader.close() here!
```

---

## 2. The `AutoCloseable` Contract
Any class implementing `java.lang.AutoCloseable` can be used within try-with-resources:
```java
public interface AutoCloseable {
    void close() throws Exception;
}
```
- It has only one method: `void close()`.
- Idempotence: Implementations are strongly encouraged to make `close()` idempotent (calling it more than once should be safe and harmless).

---

## 3. Reverse (LIFO) Closing Order
When multiple resources are declared in a single try-with-resources block, they are initialized from **left-to-right**, and closed in reverse **right-to-left (LIFO)** order:

```java
try (ResourceA a = new ResourceA();
     ResourceB b = new ResourceB()) {
    // Operations
}
```
**Execution Sequence:**
1. `ResourceA` initialized.
2. `ResourceB` initialized.
3. Body runs.
4. `ResourceB.close()` called first!
5. `ResourceA.close()` called second!

This mimics stack semantics and ensures dependent resources (e.g., statements) close before their parent dependencies (e.g., database connection).
