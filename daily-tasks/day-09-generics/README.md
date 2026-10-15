# Day 9: Generics & Try-With-Resources

**Status:** Completed  
**Progress:** 100%  
**Week:** 2 | **Sprint:** 1 | **Module:** 1  
**Deliverable:** `GenericToolkit` + `CrudRepository` Project Repository  
**Git Commit:** `day-9: generics`

---

## Learning Objectives
By the end of this day, you will be able to:
- Design and implement generic classes and interfaces (`Pair<K,V>`, `CrudRepository<T,ID>`).
- Constrain generic type parameters with upper bounds (`<T extends Number>`) and recursive bounds (`<T extends Comparable<T>>`).
- Master the **PECS principle** (Producer Extends, Consumer Super) for wildcard variance.
- Understand the JVM implementation of generics via **Type Erasure** and its practical consequences.
- Safely read and parse CSV files using `try-with-resources`.
- Implement custom `AutoCloseable` resources and demonstrate guaranteed LIFO (reverse) closing order.
- Build an enterprise-grade generic `InMemoryRepository<T,ID>` reused across any domain entity.

---

## Main Points Summary

| Concept | Description | Key Details |
| :--- | :--- | :--- |
| **Generics Fundamentals** | Compile-time parametric polymorphism | Eliminates explicit casting and converts runtime `ClassCastException` into compile-time errors. |
| **Type Erasure** | JVM compilation mechanism | Type parameters are replaced with bounds (or `Object`), bridge methods are added, and casts are inserted automatically. |
| **Bounded Types** | Parameter constraints | `<T extends Number>` restricts types to numeric subclasses; `<T extends Comparable<T>>` enforces natural sortability. |
| **PECS Principle** | Producer Extends, Consumer Super | Use `? extends T` when reading data from a collection; use `? super T` when writing data into a collection. |
| **`try-with-resources`** | Deterministic resource management | Automatically invokes `close()` on any `AutoCloseable` instance even in the presence of unhandled errors. |
| **LIFO Close Order** | Reverse teardown sequence | Multiple resources declared in a try header are closed in reverse order of their declaration. |
| **Generic Repository** | Decoupled data access abstraction | `CrudRepository<T, ID>` implemented by `InMemoryRepository<T, ID>` with generic ID extraction function. |

---

## Estimated Study Time
Total: approximately 3 hours 30 minutes.

### Suggested Schedule
- **Session 1:** Generics fundamentals, `Pair<K,V>`, `Box<T extends Number>`, and type erasure — 45 minutes.
- **Session 2:** Bounded methods (`<T extends Comparable<T>> findMax`) and PECS wildcards — 45 minutes.
- **Session 3:** `try-with-resources`, CSV file ingestion, and AutoCloseable LIFO order — 45 minutes.
- **Session 4:** Generic `CrudRepository<T, ID>` implementation, testing, and review — 75 minutes.

---

## Topics & Reference Notes

Detailed conceptual guides are provided in the `notes/` directory:
- [Generics Fundamentals & Type Erasure](./notes/generics-fundamentals-type-erasure.md)
- [Bounded Types and PECS Wildcards](./notes/bounded-types-and-pecs.md)
- [Try-With-Resources & AutoCloseable](./notes/try-with-resources-and-autocloseable.md)

---

## Project Structure & Deliverables

```text
daily-tasks/day-09-generics/
├── data/
│   └── products.csv                     # Seed data for try-with-resources parsing
├── notes/
│   ├── bounded-types-and-pecs.md
│   ├── generics-fundamentals-type-erasure.md
│   └── try-with-resources-and-autocloseable.md
├── src/
│   ├── model/
│   │   └── Product.java
│   ├── repository/
│   │   ├── CrudRepository.java          # Generic interface <T, ID>
│   │   └── InMemoryRepository.java      # Reusable in-memory repository
│   ├── resource/
│   │   ├── AuditLoggerResource.java     # Custom AutoCloseable
│   │   └── DatabaseConnectionResource.java
│   ├── toolkit/
│   │   ├── Box.java                     # Bounded Box<T extends Number>
│   │   ├── GenericToolkit.java          # PECS wildcards & <T extends Comparable<T>>
│   │   └── Pair.java                    # Generic Pair<K, V>
│   └── GenericApplication.java          # Main driver running all requirements
└── README.md
```

---

## Setup and Run Instructions

1. Navigate to the `daily-tasks/day-09-generics` directory:
   ```bash
   cd daily-tasks/day-09-generics
   ```
2. Compile all source files:
   ```bash
   javac src/model/*.java src/repository/*.java src/resource/*.java src/toolkit/*.java src/GenericApplication.java
   ```
3. Run the application:
   ```bash
   java -cp src GenericApplication
   ```
