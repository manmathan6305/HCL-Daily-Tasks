# Control Flow Statements

## 1. Conditional Statements
*   **if/else:** Executes a block of code if a condition is true, otherwise executes another block.
*   **switch:** Selects one of many code blocks to be executed.
    ```java
    // Modern Java 14+ switch expression example
    int numLetters = switch (day) {
        case MONDAY, FRIDAY, SUNDAY -> 6;
        case TUESDAY                -> 7;
        default                     -> 0;
    };
    ```

## 2. Looping Statements
*   **for loop:** Used when the number of iterations is known.
    `for (initialization; condition; increment/decrement) { ... }`
*   **while loop:** Loops as long as a specified condition is true. Use when the number of iterations is unknown.
*   **do-while loop:** A variant of the while loop. It executes the code block *at least once*, before checking if the condition is true, then it repeats the loop as long as the condition is true.
*   **enhanced for loop (for-each):** Used specifically for arrays and collections.
    `for (String item : itemsList) { ... }`

## 3. Jump/Branching Statements
*   **break:** "Jumps out" of a loop or a switch statement.
*   **continue:** "Jumps over" one iteration in the loop and continues with the next iteration.

## 4. Input Validation
When taking user input (e.g., using `Scanner`), always ensure the user doesn't enter text when you expect a number, or negative values when only positive values make sense. This prevents runtime crashes.
