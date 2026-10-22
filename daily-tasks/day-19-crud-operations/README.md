# 📅 Day 19: CRUD Operations

## 📋 Task Overview
- **Topic:** `CRUD Operations`
- **Module / Sprint:** Week 4, Sprint 2.6
- **Daily Hands-on Task:** Create an employees/departments database in MySQL; insert 30 rows; then answer 12 filtering/sorting questions (e.g. salary `BETWEEN`, names `LIKE 'A%'`, dept `IN (...)`); practice safe updates in a transaction.
- **Project Task:** Write `schema.sql` and `seed.sql` for your project (>= 20 rows per main table).
- **Deliverables:** `schema.sql, seed.sql, queries.sql`
- **Git Commit:** `day-19: crud-operations`
- **Done When Criteria:** `Scripts run cleanly from scratch`

---

## 🏗️ Architecture & Project Structure
```text
day-19-crud-operations/
├── deliverables/
│   └── query-execution-results.md      # Execution output and proof of clean run
├── notes/
│   ├── ddl-vs-dml-fundamentals.md      # DDL vs DML taxonomy and data types
│   └── safe-updates-and-filtering-guide.md # Safe updates and transactions
├── sql/
│   ├── queries.sql                     # 12 Filtering, sorting, and transaction questions
│   ├── schema.sql                      # DDL: departments and employees
│   ├── seed.sql                        # Multi-row INSERT: 6 departments, 30 employees
│   ├── project-schema.sql              # Project DDL: customers, accounts, transactions
│   └── project-seed.sql                # Project Seed: >= 20 rows per table
├── src/
│   └── DatabaseRunner.java             # Java driver executing verification
├── test_runner.py                      # Clean runner testing all SQL scripts
└── README.md
```

---

## 🚀 Execution & Verification
To execute and verify all SQL scripts from scratch:
```bash
python test_runner.py
# or via Java driver
javac -d bin src/DatabaseRunner.java && java -cp bin DatabaseRunner
```
