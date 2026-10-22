# Built-in Functional Interfaces and Composition

## 1. The Core Four Functional Interfaces (`java.util.function`)

| Interface | Method Signature | Conceptual Role | Example Real-World Usage |
| :--- | :--- | :--- | :--- |
| **`Predicate<T>`** | `boolean test(T t)` | Evaluates a condition | Filtering products by price, checking in-stock |
| **`Function<T, R>`** | `R apply(T t)` | Transforms input to output | Extracting price, converting entity to DTO |
| **`Consumer<T>`** | `void accept(T t)` | Consumes with side effects | Logging, printing, writing to output stream |
| **`Supplier<T>`** | `T get()` | Generates/supplies values | Factory methods, default fallbacks, lazy evaluation |

---

## 2. Functional Composition Combinators

### Predicate Combinators
- `predicate1.and(predicate2)`: Logical AND ($P_1 \\land P_2$). Short-circuits if $P_1$ is false.
- `predicate1.or(predicate2)`: Logical OR ($P_1 \\lor P_2$). Short-circuits if $P_1$ is true.
- `predicate.negate()`: Logical NOT ($\\neg P$).

### Function Combinators
- `f1.andThen(f2)`: Evaluates `f1` first, then passes result to `f2` ($f_2(f_1(x))$).
- `f1.compose(f2)`: Evaluates `f2` first, then passes result to `f1` ($f_1(f_2(x))$).

---

## 3. Effectively Final Variable Capture
Lambdas can access local variables from their enclosing scope only if they are **effectively final** (assigned exactly once and never modified).
```java
int minPrice = 100; // Effectively final
Predicate<Product> p = prod -> prod.getPrice() >= minPrice; // Legal!

int counter = 0;
// Consumer<String> c = s -> counter++; // COMPILE ERROR: Variable used in lambda must be effectively final!
```
