# Language Fundamentals

## 1. Variables and Constants
*   **Variables:** Named memory locations to store data.
*   **Constants:** Variables whose value cannot change once assigned. In Java, use the `final` keyword (e.g., `final int MAX_USERS = 100;`).

## 2. Eight Primitive Data Types
Java has 8 primitive types, which are not objects:
1.  `byte`: 8-bit integer (-128 to 127)
2.  `short`: 16-bit integer (-32,768 to 32,767)
3.  `int`: 32-bit integer (approx. -2 billion to 2 billion). Default choice for numbers.
4.  `long`: 64-bit integer. Use an `L` suffix (e.g., `10000000000L`).
5.  `float`: 32-bit floating-point. Use an `f` suffix (e.g., `3.14f`).
6.  `double`: 64-bit floating-point. Default choice for decimals.
7.  `char`: 16-bit Unicode character (e.g., `'A'`). Uses single quotes.
8.  `boolean`: `true` or `false`.

## 3. Operators
*   **Arithmetic:** `+`, `-`, `*`, `/`, `%` (modulo)
*   **Relational:** `==`, `!=`, `>`, `<`, `>=`, `<=`
*   **Logical:** `&&` (AND), `||` (OR), `!` (NOT)
*   **Ternary:** `condition ? value_if_true : value_if_false;`

## 4. Type Casting and Integer Overflow
*   **Implicit Casting (Widening):** Automatic conversion to a larger type (e.g., `int` to `double`).
*   **Explicit Casting (Narrowing):** Manual conversion to a smaller type, which can lose data (e.g., `int x = (int) 3.14;`).
*   **Integer Overflow:** Occurs when a calculation exceeds the maximum limit of the data type. E.g., `Integer.MAX_VALUE + 1` wraps around to `Integer.MIN_VALUE`.

## 5. Arrays
Arrays are fixed-size collections of elements of the same type.
*   **1D Array:** `int[] numbers = {1, 2, 3};`
*   **2D Array:** Used for matrices or grids. `int[][] grid = { {1, 2}, {3, 4} };`
