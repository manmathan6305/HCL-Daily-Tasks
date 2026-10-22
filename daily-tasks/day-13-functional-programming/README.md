# Day 13: Functional Programming & Filter Engine

**Status:** Completed  
**Progress:** 100%  
**Week:** 3 | **Sprint:** 2 | **Module:** 2  
**Deliverable:** `FilterEngine.java`  
**Git Commit:** `day-13: functional-programming`  
**Criteria:** Zero anonymous inner classes left where a lambda fits.  

---

## Learning Objectives
By the end of this day, you will be able to:
- Write idiomatic lambda expressions and understand effectively final variable capture.
- Master the built-in functional interfaces: `Predicate<T>`, `Function<T, R>`, `Consumer<T>`, and `Supplier<T>`.
- Compose complex predicates using `.and()`, `.or()`, and `.negate()`.
- Construct pipeline transformations using `Function.andThen()` and `Function.compose()`.
- Identify and implement all 4 kinds of Method References (`::`).
- Author custom `@FunctionalInterface` contracts and express Strategy patterns as pure lambdas.

---

## Main Points Summary

| Concept | Description | Key Details |
| :--- | :--- | :--- |
| **`Predicate<T>`** | Boolean condition evaluator | Composed via `.and()`, `.or()`, `.negate()`. Short-circuiting behavior. |
| **`Function<T, R>`** | Type transformer | Chained via `.andThen()` and `.compose()`. Transforms domain entities into formatted values. |
| **`Consumer<T>`** | Side-effect executor | Accepts an argument and returns `void`. Ideal for printing and logging pipelines. |
| **`Supplier<T>`** | Value generator | Returns a new or cached instance with zero arguments. Used for lazy fallback defaults (`orElseGet`). |
| **Method References** | Compact lambda shorthand | 4 forms: Static, Bound Instance, Unbound Instance, and Constructor (`::new`). |
| **Strategy Pattern** | Polymorphic algorithm selection | Expressed via `@FunctionalInterface` eliminating verbose anonymous classes. |

---

## Estimated Study Time
Total: approximately 3 hours 30 minutes.

### Suggested Schedule
- **Session 1:** Lambdas, syntax, and effectively final capture — 45 minutes.
- **Session 2:** The 4 Core Functional Interfaces and composition — 45 minutes.
- **Session 3:** The 4 kinds of Method References with refactoring drills — 45 minutes.
- **Session 4:** Building `FilterEngine.java`, strategy refactoring, and review — 75 minutes.

---

## Project Structure & Deliverables

```text
day-13-functional-programming/
├── notes/
│   ├── functional-interfaces-and-composition.md
│   └── method-references-and-lambdas.md
├── src/
│   ├── model/
│   │   └── ProductItem.java
│   ├── service/
│   │   └── ProductValidator.java
│   ├── strategy/
│   │   └── DiscountStrategy.java
│   └── FilterEngine.java           # Primary Deliverable
└── README.md
```

---

## Setup and Run Instructions

1. Navigate to the `day-13-functional-programming` directory:
   ```bash
   cd daily-tasks/day-13-functional-programming
   ```
2. Compile all source files:
   ```bash
   javac src/model/*.java src/service/*.java src/strategy/*.java src/FilterEngine.java
   ```
3. Run the application:
   ```bash
   java -cp src FilterEngine
   ```
