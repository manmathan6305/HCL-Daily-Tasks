# 📅 Day 21: Views, Indexing & Constraints

## 📋 Task Overview
- **Topic:** `Views, Indexing & Constraints`
- **Module / Sprint:** Week 4, Sprint 2.6
- **Daily Hands-on Task:** Add constraints to yesterday's database and prove each one rejects bad data; create a composite index and compare EXPLAIN before/after; create a reporting view; test cascading deletes.
- **Project Task:** Add all business-rule constraints, 2 justified indexes and 1 reporting view to your project schema.
- **Deliverables:** `constraints.sql + EXPLAIN screenshots`
- **Git Commit:** `day-21: views-indexing-constraints`
- **Done When Criteria:** `Every constraint tested with a failing insert`

---

## 🏗️ Architecture & Project Structure
```text
day-21-views-indexing-constraints/
├── deliverables/
│   ├── constraint-test-results.md      # Matrix proving every constraint rejects bad data
│   └── explain-analysis-report.md      # Before/after EXPLAIN query plan analysis
├── notes/
│   ├── database-constraints-and-views.md # Constraints taxonomy & view security
│   └── database-indexing-and-explain.md  # B-Tree internals and EXPLAIN guide
├── sql/
│   ├── constraints.sql                 # DDL constraints, views, cascade rules
│   └── project-constraints.sql         # Project constraints, 2 indexes, 1 view
├── src/
│   └── ConstraintValidatorRunner.java  # Java driver
├── test_runner.py                      # Automated test suite
└── README.md
```

---

## ⚙️ Compilation & Verification
```bash
python test_runner.py
# or via Java driver
javac -d bin src/ConstraintValidatorRunner.java && java -cp bin ConstraintValidatorRunner
```
