# Day 4: OOP Concepts + IDE Debugging

## 🎯 Learning Objectives
By the end of this day, you will understand the core concepts of Object-Oriented Programming (OOP) in Java and learn how to debug applications systematically.

**⏱️ Estimated Study Time:** 4 hours

## 📚 Topics to Learn
1. **Object-Oriented Programming (OOP):**
   * Classes and Objects (blueprints vs instances).
   * Fields, methods, and constructors.
   * `this` and `super` keywords.
   * Constructor overloading and chaining.
   * Encapsulation and access modifiers (`private`, `default`, `protected`, `public`).
   * Static vs instance members.
   * Packages for organizing code.
2. **IDE Debugging (Eclipse / IntelliJ / VS Code):**
   * Setting Breakpoints and Conditional Breakpoints.
   * Stepping: Step Into, Step Over, Step Out.
   * Inspecting variables, watches, and call stacks.

## 🛠️ Practical Tasks

### Task 1: Bank Account OOP Example
1. Review `src/com/hcl/bank/BankAccount.java`.
2. Notice the use of:
   * Private fields for encapsulation.
   * A static field `totalAccounts` to count instances.
   * Constructor chaining (`this(...)`).
   * Overridden `equals()` and `hashCode()` methods.
3. Compile and execute:
   ```bash
   cd src
   javac com/hcl/bank/BankAccount.java
   java com.hcl.bank.BankAccount
   ```

### Task 2: Debugging Practice
1. Open `BankAccount.java` in your IDE.
2. Set a breakpoint on the `withdraw` method.
3. Notice there is an intentional bug in the code where it allows drawing below the balance if not handled correctly. (Actually, I have included a hidden logical flaw in the printed message or balance check. Find it!)
4. Run the program in **Debug Mode**.
5. "Step Over" each line. Inspect the `balance` variable before and after the withdrawal.
6. Fix the bug you discover.

## 📝 Practice Questions
1. Why do we make fields `private` and provide public getters/setters?
   * *Answer:* This is called Encapsulation. It prevents external code from changing fields in unexpected ways and allows the class to validate data before it is assigned.
2. What is the difference between an instance variable and a `static` variable?
   * *Answer:* A static variable is shared among all objects of the class (one copy in memory). An instance variable is unique to each object.

## ✅ Completion Checklist
- [ ] Read the OOP notes and Debugging guide.
- [ ] Compiled and ran `BankAccount.java`.
- [ ] Opened the code in an IDE and set a breakpoint.
- [ ] Used Step Over/Step Into features to track the balance variable.
- [ ] Fixed the intentional bug in the `withdraw` method.
- [ ] Added screenshots of the IDE debugger in action to the deliverables folder.

## 🐛 Common Errors & Troubleshooting
* **`NullPointerException`**: You are trying to call a method on an object that hasn't been instantiated yet (it is `null`).
* **`Cannot find symbol`**: You might be missing an `import` statement for a class in a different package.

## 🧠 End-of-Day Self-Assessment
* Do I understand when to use `this()` inside a constructor?
* Can I comfortably pause a running program and inspect its state?
