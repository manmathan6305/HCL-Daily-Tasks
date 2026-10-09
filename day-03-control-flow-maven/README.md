# Day 3: Control Flow + Maven

## 🎯 Learning Objectives
By the end of this day, you will be able to control the flow of your Java programs using conditional statements and loops. You will also learn to structure a project using Maven.

**⏱️ Estimated Study Time:** 4 hours

## 📚 Topics to Learn
1. **Control Flow Statements:**
   * Conditional: `if/else`, `switch` (including modern switch expressions).
   * Looping: `for`, `while`, `do-while`, and enhanced `for` loop (for-each).
   * Branching: `break`, `continue`.
   * Validating user input securely.
2. **Maven Build Tool:**
   * Maven project structure (e.g., `src/main/java`).
   * The `pom.xml` file and dependencies.
   * Maven build lifecycle (`clean`, `compile`, `test`, `package`, `install`).
   * Maven profiles (e.g., dev vs. prod configurations).

## 🛠️ Practical Tasks

### Task 1: ATM Simulator
1. Build `src/main/java/ATMSimulator.java` inside a standard Maven directory structure.
2. Implement a console-based menu using a `do-while` loop.
3. Include options for: Balance Enquiry, Deposit, Withdrawal, and Exit using a `switch` statement.
4. Validate inputs (e.g., negative deposit amounts).
5. Require a PIN at the start, allowing a maximum of 3 attempts using a `while` loop.

### Task 2: Maven Conversion
1. Convert the project into a Maven project by reviewing the `pom.xml` file.
2. Ensure the standard directory structure `src/main/java` is used.
3. **Compile and Package** using Maven:
   ```bash
   mvn clean package
   ```
4. **Run the application**:
   ```bash
   java -cp target/atm-simulator-1.0-SNAPSHOT.jar ATMSimulator
   ```
5. Experiment with Maven profiles (review the `dev` and `prod` profiles in `pom.xml`).

## 📝 Practice Questions
1. When should you use a `do-while` loop instead of a `while` loop?
   * *Answer:* A `do-while` loop is guaranteed to execute at least once, making it ideal for displaying menus where the user must make a choice before exiting.
2. What does `mvn clean` do?
   * *Answer:* It deletes the `target` directory, removing all previously compiled classes and build artifacts, ensuring a fresh build.

## ✅ Completion Checklist
- [ ] Read the notes on Control Flow and Maven.
- [ ] Implemented `ATMSimulator.java` with loop/switch logic and input validation.
- [ ] Understood the `pom.xml` configuration.
- [ ] Ran `mvn clean package` successfully.
- [ ] Ran the packaged jar file and tested the ATM functionality.
- [ ] Added screenshots of the Maven build success to the deliverables folder.

## 🐛 Common Errors & Troubleshooting
* **`InputMismatchException`**: Using `Scanner.nextInt()` but entering text. *Fix:* Always handle invalid input types using `Scanner.hasNextInt()` or `try-catch`.
* **`mvn: command not found`**: You need to install Apache Maven and add its `bin` folder to your system's `PATH`.

## 🧠 End-of-Day Self-Assessment
* Can I explain the difference between `break` and `continue`?
* Do I understand the purpose of `pom.xml` in a Java project?
