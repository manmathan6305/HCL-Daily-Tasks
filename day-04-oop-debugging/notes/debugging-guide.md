# IDE Debugging Guide

Debugging is the process of finding and resolving bugs (defects or problems) that prevent correct operation of software.

## 1. Breakpoints
A breakpoint is an intentional stopping or pausing place in a program.
*   *How to set:* Click in the left margin (gutter) next to a line of code in your IDE. A red dot will appear.
*   *Conditional Breakpoints:* Right-click the red dot. You can set a condition (e.g., `balance < 0`) so the program only pauses if that condition is true.

## 2. Running in Debug Mode
Instead of clicking the regular "Run" (play) button, click the "Debug" (bug icon) button. The program will run normally until it hits a line with a breakpoint.

## 3. Stepping Commands
Once paused, you control the execution:
*   **Step Over (F8):** Executes the current line and pauses at the next line. If the current line is a method call, it runs the *entire* method in the background and pauses on the next line of the current method.
*   **Step Into (F7):** If the current line contains a method call, it goes *inside* that method and pauses on its first line.
*   **Step Out (Shift+F8):** Finishes executing the current method and pauses at the line where the method was originally called.

## 4. Inspecting State
*   **Variables View:** Shows the current values of all local variables and fields in the current scope.
*   **Watches:** You can add specific expressions (e.g., `balance - amount`) to explicitly track their values as you step through the code.
*   **Call Stack:** Shows the sequence of method calls that led to the current point of execution. Extremely useful for understanding *how* you arrived at an error.
