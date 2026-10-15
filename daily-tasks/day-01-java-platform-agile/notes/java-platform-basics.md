# Java Platform Basics

## 1. JDK vs JRE vs JVM
*   **JVM (Java Virtual Machine):** The engine that executes Java bytecode. It is platform-dependent (there's a different JVM for Windows, Mac, Linux), but it allows the bytecode to be platform-independent.
*   **JRE (Java Runtime Environment):** Contains the JVM plus core Java libraries and components needed to *run* a Java program. (JRE = JVM + Libraries).
*   **JDK (Java Development Kit):** The full toolkit for Java developers. It includes the JRE plus development tools like the compiler (`javac`), archiver (`jar`), and documentation generators (`javadoc`). (JDK = JRE + Development Tools).

## 2. Java Compilation Flow
1.  **Source Code:** You write `.java` files in plain text.
2.  **Compilation:** The compiler (`javac`) converts `.java` files into `.class` files.
3.  **Bytecode:** The `.class` files contain bytecode, an intermediate language that is not machine-specific.
4.  **Execution:** The JVM reads the bytecode and translates it into native machine code for the specific OS.

## 3. JVM Architecture Deep Dive
*   **Class Loader Subsystem:** Loads, links, and initializes `.class` files into memory when they are needed.
*   **Runtime Data Areas:**
    *   *Method Area:* Stores class-level data, including static variables and method bytecode.
    *   *Heap Area:* Where objects and arrays are created. Garbage collection happens here.
    *   *Stack Area:* Stores local variables and partial results for thread execution. Each thread has its own stack.
    *   *PC Register:* Keeps track of the current instruction being executed.
    *   *Native Method Stack:* For native (C/C++) method execution.
*   **Execution Engine:**
    *   *Interpreter:* Reads bytecode stream and executes it line by line. Can be slow.
    *   *JIT (Just-In-Time) Compiler:* Optimizes execution by finding "hotspots" (frequently executed code) and compiling them directly into native machine code.
    *   *Garbage Collector (GC):* Automatically frees memory by destroying objects that are no longer referenced.
