# Day 17 Deliverable: Modern Java Features Deep Dive (Java 9 to 21)

## 1. Sealed Types & Pattern-Matching Switch
Sealed interfaces (`sealed interface Shape permits Circle, Rectangle, Triangle`) restrict which types can implement them. This enables the compiler to verify **exhaustiveness**:
```java
return switch (shape) {
    case Circle c    -> Math.PI * c.radius() * c.radius();
    case Rectangle r -> r.width() * r.height();
    case Triangle t  -> calculateHeron(t);
};
```
No `default` clause is needed. If a developer attempts to add a new `Hexagon` without updating the switch, compilation fails instantly with a compile-time exhaustiveness error.

---

## 2. Records with Compact Constructors
Records reduce boilerplate while enforcing immutability:
```java
public record Circle(double radius) implements Shape {
    public Circle {
        if (radius <= 0.0) {
            throw new IllegalArgumentException("radius must be positive");
        }
    }
}
```
No need to repeat field assignments (`this.radius = radius`). Validation occurs before field binding.

---

## 3. Text Blocks
Eliminates tedious `\n` escaping and `+` string concatenations, automatically managing incidental whitespace with `stripIndent()`.

---

## 4. Immutable Collections (`List.of`, `Set.of`, `Map.of`)
Unlike `Collections.unmodifiableList()`, which is a view wrapping a mutable backing collection, Java 9 `List.of()` creates truly immutable, space-efficient internal data structures (`List12`, `ListN`) that throw `UnsupportedOperationException` immediately upon mutation.
