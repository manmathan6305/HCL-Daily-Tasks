# Day 6: Exception Handling + AI-Assisted Debugging

**Status:** Not Started  
**Progress:** 0%

## Learning Objectives
By the end of this day, you will be able to:
- Use `try`, `catch`, and `finally` to handle errors gracefully.
- Understand the difference between `throw` and `throws`.
- Differentiate and implement Checked and Unchecked exceptions.
- Implement Exception chaining without losing the original stack trace.
- Read and interpret Java stack traces.
- Use AI tools like GitHub Copilot to debug stack traces and verify their outputs.

## Estimated Study Time
Total: approximately 3 hours 30 minutes.

### Suggested Schedule
- **Session 1:** Exception fundamentals — 45 minutes.
- **Session 2:** Custom exceptions and Order Processor — 90 minutes.
- **Session 3:** Debugging and stack traces with AI — 45 minutes.
- **Session 4:** Testing, review, and notes — 30 minutes.

## Topics

Detailed notes are provided in the `notes/` directory:
- [Exception Handling Fundamentals](./notes/exception-handling.md)
- [Checked vs Unchecked Exceptions](./notes/checked-vs-unchecked.md)
- [AI-Assisted Stack-Trace Debugging](./notes/ai-stack-trace-debugging.md)

## Setup and Run Instructions
This project uses standard Java 21 syntax.
1. Navigate to the `day-06-exception-handling` directory.
2. Compile the application:
   ```bash
   javac src/exceptions/*.java src/OrderProcessor.java
   ```
3. Run the application:
   ```bash
   java -cp src OrderProcessor
   ```

## Menu-Driven Application Instructions
The `OrderProcessor.java` provides a menu-driven interface to:
1. View available stock.
2. Place an order (by specifying a quantity).
3. Exit the application.

### Business Rules
- Quantity must be greater than 0. If not, throws `InvalidQuantityException` (unchecked).
- Quantity cannot exceed stock. If it does, throws `InsufficientStockException` (checked).
- Non-numeric inputs are handled using multi-catch (`NumberFormatException` and `IllegalStateException`).
- The program recovers and continues displaying the menu upon any validation failure.
- An audit message is printed via a `finally` block for every order attempt.

## Practical Exercises

Run the application and try the following inputs:

1. **Place a valid order.**
   - *Expected Behavior:* Stock decreases, success message appears, and the audit log records a successful attempt. The menu reappears.
2. **Enter zero as the quantity.**
   - *Expected Behavior:* Validation Error is printed from `InvalidQuantityException`. The audit log records a failed attempt. The menu reappears.
3. **Enter a negative quantity.**
   - *Expected Behavior:* Same as entering zero. `InvalidQuantityException` is thrown.
4. **Request more stock than is available.**
   - *Expected Behavior:* Stock Error is printed from `InsufficientStockException`. The audit log records a failed attempt. The menu reappears.
5. **Enter letters instead of a number.**
   - *Expected Behavior:* The application catches `NumberFormatException`, prints an error indicating invalid numeric quantity, and prints the chained exception for demonstration. The audit log records failure.
6. **Trigger two different exception types and explain how they are handled.**
   - *Expected Behavior:* E.g., `InvalidQuantityException` (custom unchecked) is caught by a specific catch block for validation, while `NumberFormatException` is caught in a multi-catch block. Both ensure the menu doesn't crash.
7. **Confirm that the audit message appears after both successful and failed attempts.**
   - *Expected Behavior:* The `finally` block in `processOrderInput` runs unconditionally, printing the audit message.
8. **Confirm that the application returns to the menu after recoverable errors.**
   - *Expected Behavior:* The `while (true)` loop in `displayMenu` continues.
9. **Explain the difference between `throw` and `throws`.**
   - *Explanation:* `throw` actually throws an exception instance inside a method body. `throws` is part of the method signature declaring that the method can throw a checked exception.
10. **Explain why `InsufficientStockException` is checked while `InvalidQuantityException` is unchecked.**
    - *Explanation:* Stock issues are external constraints that the caller must gracefully handle (thus Checked). Invalid quantities are programming logic/input errors that shouldn't happen if the client verified inputs (thus Unchecked).

## AI Stack-Trace Debugging Activity
Review the [AI Stack-Trace Debugging](./notes/ai-stack-trace-debugging.md) guide.

**Your Task:**
Use this prompt in GitHub Copilot Chat with a stack trace of your choice:
> "Explain this Java stack trace in simple English. Identify the exception type, the likely root cause, the first application-code line to inspect, and the smallest safe fix. Explain why the error occurs. Do not assume facts that are not shown in the trace. Suggest how I can verify the fix with a test. Here is the stack trace: [paste trace]"

**Record:**
- The original problem.
- The AI's explanation.
- What was correct.
- What was incorrect or incomplete.
- The verified fix and test result.

## Interview Questions

1. **What is the difference between `Checked` and `Unchecked` exceptions?**
   *Answer:* Checked exceptions extend `Exception` and must be handled or declared. Unchecked exceptions extend `RuntimeException` and don't enforce handling at compile time.
2. **Can a `finally` block fail to execute?**
   *Answer:* Yes, if `System.exit()` is called, or if the JVM crashes/loses power.
3. **What is exception chaining and why is it useful?**
   *Answer:* Passing an exception as a `cause` to a new exception. It preserves the original stack trace while allowing higher levels to deal with an abstraction (like wrapping `SQLException` in a `DatabaseException`).

## Completion Checklist
- [ ] Read all notes.
- [ ] Compile and run `OrderProcessor`.
- [ ] Complete all practical exercises.
- [ ] Perform the AI stack trace debugging task.
- [ ] Answer interview and self-assessment questions.

## Deliverables & Screenshot Checklist
- [ ] `.gitkeep` file in the `deliverables` directory.
- [ ] Screenshot of a successful order run saved in `deliverables/`.
- [ ] Screenshot of an invalid order recovering successfully saved in `deliverables/`.
- [ ] Screenshot of Copilot Chat explaining a stack trace saved in `deliverables/`.

## Common Errors & Troubleshooting
- **`cannot find symbol` during compile:** Ensure you are in the `day-06-exception-handling` directory and running `javac src/exceptions/*.java src/OrderProcessor.java`.
- **`Alternatives in a multi-catch statement cannot be related by subclassing`:** Make sure the exceptions inside a multi-catch block (e.g., `NumberFormatException | NullPointerException`) don't inherit from one another.

## Self-Assessment Questions
1. Could I write a try-catch block from memory?
2. Do I understand when to create a custom unchecked exception vs a custom checked exception?
3. Am I comfortable reading a stack trace to find the exact line in my code causing the error?
