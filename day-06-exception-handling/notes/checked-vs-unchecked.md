# Checked vs Unchecked Exceptions

## Comparison Table
| Feature | Checked Exceptions | Unchecked Exceptions |
| --- | --- | --- |
| **Inheritance** | Extend `java.lang.Exception` (but not `RuntimeException`). | Extend `java.lang.RuntimeException`. |
| **Compiler Check**| Checked by the compiler at compile-time. | Not checked by the compiler. |
| **Handling** | Must be either caught with `try-catch` or declared in the method signature using `throws`. | Handling is optional, though recommended where recovery is possible. |
| **Use Case** | Used for recoverable errors outside the program's control (e.g., File I/O, Network issues). | Used for programming logic errors (e.g., null pointers, invalid arguments). |
| **Examples** | `IOException`, `SQLException`, `ClassNotFoundException` | `NullPointerException`, `IllegalArgumentException`, `ArithmeticException` |

## Custom Checked Exceptions
To create a custom checked exception, create a class that extends `Exception`.

**Example:**
```java
public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
    
    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

## Custom Unchecked Exceptions
To create a custom unchecked exception, create a class that extends `RuntimeException`.

**Example:**
```java
public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(String message) {
        super(message);
    }
    
    public InvalidQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
```
