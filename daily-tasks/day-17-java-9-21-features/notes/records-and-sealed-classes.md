# Records and Sealed Types in Modern Java

## 1. Records (Java 16+)
- **Purpose:** Transparent, immutable carriers for shallowly immutable data.
- **Automatically Generated:** Canonical constructor, getters (e.g. `radius()` instead of `getRadius()`), `equals()`, `hashCode()`, and `toString()`.
- **Restrictions:**
  - Cannot extend any class (implicitly extends `java.lang.Record`).
  - Cannot declare non-static instance fields.
  - Can implement interfaces.

---

## 2. Sealed Classes and Interfaces (Java 17+)
- Declare `sealed` keyword with `permits` clause:
```java
public sealed interface Shape permits Circle, Rectangle, Triangle {}
```
- Subtypes must be marked `final`, `sealed`, or `non-sealed`.
- Records are implicitly `final`, making them ideal subtypes for sealed hierarchies.
