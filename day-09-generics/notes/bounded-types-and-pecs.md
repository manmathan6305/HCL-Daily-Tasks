# Bounded Types and the PECS Principle

## 1. Bounded Type Parameters
Restricts the types that can be passed as type arguments.

### Upper Bounds (`<T extends SuperType>`)
Restricts `T` to `SuperType` or any of its subclasses:
```java
public class Box<T extends Number> {
    private T value;
    public double doubleValue() {
        return value.doubleValue(); // Safe because T is guaranteed to extend Number
    }
}
```

### Mutually Bounded Types (`<T extends Comparable<T>>`)
Guarantees that `T` can be compared with instances of itself:
```java
public static <T extends Comparable<T>> Optional<T> findMax(Collection<T> collection) { ... }
```

---

## 2. The PECS Principle (Producer Extends, Consumer Super)
Formulated by Joshua Bloch in *Effective Java*:
> **"PECS stands for Producer-Extends, Consumer-Super."**

### Producer Extends (`? extends T`)
Use `? extends T` when your method reads values **from** the collection (the collection is a *producer*):
```java
// Reads numbers from list; list produces numbers
public static double sumOfNumbers(List<? extends Number> list) {
    double sum = 0.0;
    for (Number n : list) {
        sum += n.doubleValue();
    }
    return sum;
}
```
*Rule:* You cannot add anything (except `null`) to a `List<? extends T>`.

### Consumer Super (`? super T`)
Use `? super T` when your method writes values **into** the collection (the collection is a *consumer*):
```java
// Writes integers into list; list consumes integers
public static void addIntegers(List<? super Integer> list, int count) {
    for (int i = 0; i < count; i++) {
        list.add(i); // Safe because any superclass of Integer can hold an Integer
    }
}
```

### Summary Table

| Wildcard | Role | Operations Permitted | Example |
| :--- | :--- | :--- | :--- |
| `List<? extends T>` | Producer | Read elements as `T`; No writing | `Collections.copy(dest, src)` |
| `List<? super T>` | Consumer | Write elements of type `T`; Reads as `Object` | `dest` in `Collections.copy` |
| `List<?>` | Unbounded | Read as `Object`; Size inquiries | `printCollection(Collection<?>)` |
