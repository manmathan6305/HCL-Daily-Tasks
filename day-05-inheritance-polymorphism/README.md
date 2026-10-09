# Day 5: Inheritance & Polymorphism + Git Branching/Merging

## 🎯 Learning Objectives
By the end of this day, you will understand how to reuse code and create flexible designs using inheritance, interfaces, and polymorphism in Java. You will also learn advanced Git workflows including branching, merging, and resolving conflicts.

**⏱️ Estimated Study Time:** 4-5 hours

## 📚 Topics to Learn
1. **Inheritance & Interfaces:**
   * `extends` keyword and abstract classes.
   * `implements` keyword and interfaces.
   * Abstract classes vs Interfaces.
2. **Polymorphism:**
   * Compile-time polymorphism (Method Overloading).
   * Runtime polymorphism (Method Overriding and dynamic method dispatch).
   * The `@Override` annotation.
3. **Advanced Git:**
   * Creating and switching branches (`git branch`, `git checkout` / `git switch`).
   * Merging branches (`git merge`).
   * Understanding and resolving merge conflicts.

## 🛠️ Practical Tasks

### Task 1: Payment Processing System
1. Review the code in `src/com/hcl/payment/PaymentDemo.java`.
2. Study the hierarchy:
   * `Refundable` (Interface)
   * `Payment` (Abstract Class)
   * `CardPayment`, `UPIPayment`, `CashPayment` (Concrete subclasses)
3. Notice how **Runtime Polymorphism** allows us to treat all payment types uniformly in a loop.
4. Notice **Method Overloading** in the `PaymentDemo` class where `processReceipt` has multiple forms.
5. Compile and execute:
   ```bash
   cd src
   javac com/hcl/payment/PaymentDemo.java
   java com.hcl.payment.PaymentDemo
   ```

### Task 2: Git Branching & Merge Conflict Practice
1. Create a new branch called `feature-discount`:
   ```bash
   git switch -c feature-discount
   ```
2. Modify a file (e.g., add a comment in `PaymentDemo.java`), commit the change.
3. Switch back to `main`:
   ```bash
   git switch main
   ```
4. Modify the *exact same line* in `PaymentDemo.java` on the `main` branch, and commit.
5. Try to merge the feature branch:
   ```bash
   git merge feature-discount
   ```
6. **Resolve the conflict!** Open the file, look for the `<<<<<<<`, `=======`, and `>>>>>>>` markers. Keep the code you want, delete the markers, save, and commit.

## 📝 Practice Questions
1. Can you instantiate an abstract class?
   * *Answer:* No, abstract classes cannot be instantiated using `new`. They are meant to be subclassed.
2. What is a Git merge conflict?
   * *Answer:* It occurs when Git is unable to automatically resolve differences in code between two commits (usually because the exact same line was modified differently in both branches).

## ✅ Completion Checklist
- [ ] Read the notes on Inheritance, Polymorphism, and Git Branching.
- [ ] Compiled and ran `PaymentDemo.java`.
- [ ] Created a UML class diagram (or text hierarchy) in the deliverables folder.
- [ ] Successfully created a Git branch and switched to it.
- [ ] Intentionally caused a Git merge conflict.
- [ ] Resolved the merge conflict and committed the final result.
- [ ] Added a screenshot of the conflict resolution to the deliverables folder.

## 🐛 Common Errors & Troubleshooting
* **`Cannot instantiate the type Payment`**: You tried to use `new Payment()`, but `Payment` is abstract! You must instantiate a concrete subclass like `new CardPayment()`.
* **Git says "You have unmerged paths"**: You are in the middle of a conflict resolution. Edit the conflicted files, `git add` them, and then `git commit`.

## 🧠 End-of-Day Self-Assessment
* Do I understand when to use an Interface versus an Abstract Class?
* Can I calmly resolve a merge conflict without deleting someone else's code by accident?
