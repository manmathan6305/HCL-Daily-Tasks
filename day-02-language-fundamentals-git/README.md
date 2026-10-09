# Day 2: Language Fundamentals + Git Fundamentals

## 🎯 Learning Objectives
By the end of this day, you will be able to write basic Java programs using variables, operators, and arrays. You will also understand and practice basic Git commands for version control.

**⏱️ Estimated Study Time:** 4 hours

## 📚 Topics to Learn
1. **Java Language Fundamentals:**
   * Variables and Constants (`final`).
   * 8 Primitive Data Types (byte, short, int, long, float, double, char, boolean) and their ranges.
   * Operators (arithmetic, relational, logical, assignment, ternary) and precedence.
   * Type casting (implicit and explicit) and integer overflow.
   * 1D and 2D arrays.
2. **Git Fundamentals:**
   * Working directory vs Staging area vs Local repository.
   * Basic commands: `git status`, `git add`, `git commit`, `git log`, `git push`, `git pull`.
   * Remotes and `.gitignore`.

## 🛠️ Practical Tasks

### Task 1: Monthly Usage Analyser
1. Build `src/MonthlyUsageAnalyser.java`.
2. Use a 12-month integer array to store mock data (e.g., electricity or data usage).
3. Calculate the total, average, maximum, and minimum usage.
4. Use constants (e.g., `final int BASE_FEE`) for business rules.
5. Use a ternary operator to assign a "Grade" (e.g., High or Low usage based on the average).
6. Demonstrate integer overflow and correct it using the `long` data type.
7. Use a 2D array to represent usage across 3 different houses.
8. **Compile and execute**:
   ```bash
   cd src
   javac MonthlyUsageAnalyser.java
   java MonthlyUsageAnalyser
   ```

### Task 2: Git Practice
1. Check the status of your repository: `git status`
2. Stage the new files you created: `git add .`
3. Commit with a meaningful message: `git commit -m "feat: implement MonthlyUsageAnalyser for Day 2"`
4. Push to GitHub: `git push origin main`

## 📝 Practice Questions
1. What happens when you add 1 to `Integer.MAX_VALUE`?
   * *Answer:* Integer overflow occurs. It wraps around to `Integer.MIN_VALUE` (a negative number).
2. What is the difference between `git add` and `git commit`?
   * *Answer:* `git add` moves changes to the staging area. `git commit` permanently saves those staged changes to your local repository history.

## ✅ Completion Checklist
- [ ] Read the notes on Language Fundamentals and Git.
- [ ] Implemented `MonthlyUsageAnalyser.java`.
- [ ] Successfully ran the program and verified outputs.
- [ ] Added Git command reference notes.
- [ ] Staged, committed, and pushed the code to GitHub.
- [ ] Added screenshots of program output to the deliverables folder.

## 🐛 Common Errors & Troubleshooting
* **`ArrayIndexOutOfBoundsException`**: You are trying to access an array index that doesn't exist (remember, arrays are 0-indexed!).
* **`git push` fails**: You might need to `git pull` first if there are changes on the remote repository that you don't have locally.

## 🧠 End-of-Day Self-Assessment
* Do I know when to use an `int` versus a `long`?
* Can I explain the flow of code from my working directory to GitHub?
