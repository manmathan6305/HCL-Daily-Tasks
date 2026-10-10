# Exception Handling in Java

## try, catch, and finally
Exceptions in Java are events that disrupt the normal flow of instructions. We handle them using `try`, `catch`, and `finally` blocks.

- **`try`**: Contains the code that might throw an exception.
- **`catch`**: Contains the code that executes if an exception of a matching type is thrown.
- **`finally`**: Contains code that *always* executes, regardless of whether an exception occurred. It's typically used for cleanup (closing files, network connections, etc.).

**Example:**
```java
public class Example {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // Throws ArithmeticException
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero.");
        } finally {
            System.out.println("Cleanup: Execution completed.");
        }
    }
}
```
**Expected Output:**
```
Error: Cannot divide by zero.
Cleanup: Execution completed.
```
*Note on `finally`: It may not execute if the JVM exits forcefully (e.g., `System.exit(0)`) or crashes.*

## throw vs throws
| Feature | `throw` | `throws` |
| --- | --- | --- |
| **Purpose** | Used to explicitly trigger (throw) an exception. | Used in a method signature to declare that the method might throw exceptions. |
| **Location** | Used inside a method body. | Used in the method declaration header. |
| **Usage** | Followed by an instance of an Exception class (`throw new Exception()`). | Followed by Exception class names (`throws IOException, SQLException`). |

**Example:**
```java
public void checkAge(int age) throws IllegalArgumentException {
    if (age < 18) {
        throw new IllegalArgumentException("Age must be 18 or older.");
    }
}
```

## Exception Propagation
When an exception is thrown in a method and not caught there, it propagates (moves) up the call stack to the method that called it, until it is caught or reaches the main method (causing the program to crash).

## Multiple Catch Blocks and Multi-Catch
You can catch different exceptions with multiple `catch` blocks or combine them using a multi-catch (introduced in Java 7).

**Example:**
```java
try {
    String numberStr = "abc";
    int num = Integer.parseInt(numberStr);
} catch (NumberFormatException | NullPointerException e) {
    System.out.println("Invalid input format or null value: " + e.getMessage());
}
```
*Common Mistake: Catching a broader exception (like `Exception`) before a more specific one (like `NumberFormatException`). The specific exceptions must be caught first.*

## Exception Chaining
Exception chaining allows you to associate one exception with another (its cause). This is useful when a lower-level exception (e.g., `SQLException`) is caught and wrapped into a higher-level business exception (e.g., `DatabaseConnectionException`) without losing the original stack trace.

**Example:**
```java
try {
    // some low-level operation
    throw new IOException("File not found");
} catch (IOException e) {
    // Preserve the original cause
    throw new RuntimeException("Failed to load configuration", e);
}
```
