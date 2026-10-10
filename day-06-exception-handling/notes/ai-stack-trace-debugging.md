# AI-Assisted Stack-Trace Debugging

Reading stack traces can be intimidating, but a stack trace is simply a report of the active method calls at the time an error occurred. Read it from top to bottom. The top line tells you the *type of exception* and the *error message*. Below that is the stack trace. Look for the first line that references your own application code.

## Using GitHub Copilot Chat to Understand Stack Traces
You can use AI tools like GitHub Copilot Chat to analyze stack traces. However, remember to verify the AI's suggestions instead of blindly trusting them.

### Prompt to use in Copilot Chat:
> "Explain this Java stack trace in simple English. Identify the exception type, the likely root cause, the first application-code line to inspect, and the smallest safe fix. Explain why the error occurs. Do not assume facts that are not shown in the trace. Suggest how I can verify the fix with a test. Here is the stack trace: [paste trace]"

---

## Scenario 1: NullPointerException

**Buggy Code:**
```java
public class OrderProcessor {
    private String customerName;
    
    public void printCustomerName() {
        System.out.println("Customer name length: " + customerName.length());
    }
    
    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        processor.printCustomerName();
    }
}
```

**Stack Trace:**
```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "this.customerName" is null
        at OrderProcessor.printCustomerName(OrderProcessor.java:5)
        at OrderProcessor.main(OrderProcessor.java:10)
```

**Sample AI Explanation (For Learning Purposes):**
* Exception Type: `NullPointerException`
* Root Cause: You are trying to call `.length()` on the variable `customerName`, but `customerName` is `null` (it has not been initialized).
* Line to Inspect: `OrderProcessor.java:5`
* Smallest Safe Fix: Initialize `customerName` before using it, for example in a constructor: `public OrderProcessor() { this.customerName = "Guest"; }`, or add a null check before calling `.length()`.

**Verification & Fix:**
* *Identifying the source line*: We check line 5 of `OrderProcessor.java` and confirm `customerName` is indeed not assigned a value.
* *Corrected Implementation*:
```java
public class OrderProcessor {
    private String customerName = "Guest"; // Fix: Initialized
    
    public void printCustomerName() {
        System.out.println("Customer name length: " + customerName.length());
    }
}
```

---

## Scenario 2: NumberFormatException

**Buggy Code:**
```java
public class StringParser {
    public static void main(String[] args) {
        String quantityInput = "ten";
        int quantity = Integer.parseInt(quantityInput);
        System.out.println("Quantity: " + quantity);
    }
}
```

**Stack Trace:**
```
Exception in thread "main" java.lang.NumberFormatException: For input string: "ten"
        at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
        at java.base/java.lang.Integer.parseInt(Integer.java:588)
        at java.base/java.lang.Integer.parseInt(Integer.java:781)
        at StringParser.main(StringParser.java:4)
```

**Sample AI Explanation (For Learning Purposes):**
* Exception Type: `NumberFormatException`
* Root Cause: The application is trying to convert the text "ten" into an integer using `Integer.parseInt()`. Since "ten" is not composed of numeric digits, it fails.
* Line to Inspect: `StringParser.java:4`
* Smallest Safe Fix: Ensure the string contains valid digits (e.g., `"10"`), or wrap the parsing logic in a `try-catch` block to handle invalid inputs gracefully.

**Verification & Fix:**
* *Identifying the source line*: While the top lines of the stack trace point to `java.base` (internal Java code), the first line from our application is `StringParser.java:4`.
* *Corrected Implementation*:
```java
public class StringParser {
    public static void main(String[] args) {
        String quantityInput = "ten";
        try {
            int quantity = Integer.parseInt(quantityInput);
            System.out.println("Quantity: " + quantity);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Please provide digits.");
        }
    }
}
```

### Checklist for Identifying Incorrect AI Claims
- Does the suggested fix apply to my exact codebase?
- Did the AI assume a specific framework (like Spring) when I'm using core Java?
- Is the proposed line number correct according to my local file?
- Does the fix simply swallow the exception without solving the root issue?
