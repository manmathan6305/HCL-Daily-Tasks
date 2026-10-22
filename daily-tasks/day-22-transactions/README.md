# 📅 Day 22: Transactions

## 📋 Task Overview
- **Topic:** `Transactions`
- **Module / Sprint:** Week 4, Sprint 2.6
- **Daily Hands-on Task:** Bank Transfer Lab: transfer money between accounts with COMMIT and a forced failure with ROLLBACK; open two MySQL sessions to demonstrate a lost update and fix it with `SELECT ... FOR UPDATE`.
- **Project Task:** Write the transaction for your project's most critical operation (the concurrency-sensitive NFR) and demonstrate rollback.
- **Deliverables:** `transactions.sql + two-session screenshots`
- **Git Commit:** `day-22: transactions`
- **Done When Criteria:** `Rollback leaves data consistent`

---

## 🏗️ Architecture & Project Structure
```text
day-22-transactions/
├── deliverables/
│   ├── acid-verification-report.md      # Mathematical proof of balance conservation
│   └── two-session-concurrency-drill.md # Terminal transcript of lost update vs FOR UPDATE
├── notes/
│   ├── acid-properties-and-isolation-levels.md # ACID taxonomy & ANSI isolation levels
│   └── pessimistic-vs-optimistic-locking.md    # Locking strategies
├── sql/
│   ├── critical-project-transaction.sql # Mission-critical project transaction
│   └── transactions.sql                 # Bank transfer lab, commit, rollback, savepoints
├── src/
│   └── BankTransferSimulation.java      # Multithreaded Java simulation
├── test_runner.py                       # Automated test script
└── README.md
```

---

## ⚙️ Compilation & Verification
```bash
python test_runner.py
# or via Java driver
javac -d bin src/BankTransferSimulation.java && java -cp bin BankTransferSimulation
```
