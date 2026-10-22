# 📅 Day 20: Joins & Aggregations

## 📋 Task Overview
- **Topic:** `Joins & Aggregations`
- **Module / Sprint:** Week 4, Sprint 2.6
- **Daily Hands-on Task:** Answer 10 reporting questions on an e-commerce sample DB: customers with no orders (`LEFT JOIN + IS NULL`), revenue per category, categories with avg order > X (`HAVING`), employee-manager pairs (`self join`), full list of products and suppliers (`UNION`).
- **Project Task:** Write 5 business reports for your project with `JOIN + GROUP BY/HAVING`.
- **Deliverables:** `joins.sql + project-reports.sql`
- **Git Commit:** `day-20: joins-aggregations`
- **Done When Criteria:** `Correct row counts; no duplicate rows from joins`

---

## 🏗️ Architecture & Project Structure
```text
day-20-joins-aggregations/
├── deliverables/
│   └── reporting-results.md            # Detailed row counts and verification matrix
├── notes/
│   ├── aggregations-group-by-having.md # Execution pipeline and GROUP BY rules
│   └── sql-joins-visual-guide.md       # Venn diagrams, outer joins, Cartesian pitfalls
├── sql/
│   ├── joins.sql                       # 10 E-commerce reporting queries
│   ├── project-reports.sql             # 5 Project reports (JOIN + GROUP BY/HAVING)
│   ├── schema.sql                      # E-commerce schema
│   └── seed.sql                        # Test dataset for deterministic verification
├── src/
│   └── JoinReportsRunner.java          # Java test runner
├── test_runner.py                      # Automated validation script
└── README.md
```

---

## ⚙️ Compilation & Verification
```bash
python test_runner.py
# or via Java driver
javac -d bin src/JoinReportsRunner.java && java -cp bin JoinReportsRunner
```
