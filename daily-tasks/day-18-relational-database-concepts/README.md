# 📅 Day 18: Relational Database Concepts

## 📋 Task Overview
- **Topic:** `Relational Database Concepts`
- **Module / Sprint:** Week 3, Sprint 2.3
- **Daily Hands-on Task:** Normalization Workshop: take a flat "college_registrations" spreadsheet (student, courses, faculty, fees in one row) and normalize it step-by-step to 3NF; draw the ER diagram.
- **Deliverables:** `Normalization worksheet + project ER diagram`
- **Git Commit:** `day-18: relational-database-concepts`
- **Done When Criteria:** `Final design in 3NF with justified keys`

---

## 🏗️ Project Structure & Deliverables
```text
day-18-relational-database-concepts/
├── deliverables/
│   ├── normalization-worksheet.md      # Step-by-step 0NF -> 1NF -> 2NF -> 3NF analysis
│   └── project-er-diagram.md           # Full Mermaid ER diagram & cardinality table
├── notes/
│   ├── database-normalization-1nf-2nf-3nf.md # Normalization theory & Armstrong axioms
│   └── rdbms-vs-nosql-tradeoffs.md     # ACID vs BASE, schema trade-offs
├── src/
│   └── NormalizationSimulator.java     # Executable Java simulation of 1NF vs 3NF
└── README.md
```

---

## 🚀 Key Accomplishments
1. **Full 3NF Normalization:**
   - Flattened multi-valued arrays into 1NF atomic rows.
   - Eliminated partial dependencies in 2NF (`students`, `courses`, `registrations`).
   - Eliminated transitive dependencies in 3NF (`departments`, `faculty`, `students`, `courses`, `registrations`, `fee_payments`).
2. **Anomaly Elimination Proven:**
   - Insertion anomaly resolved (courses and faculty inserted independently).
   - Update anomaly resolved (faculty office updated in exactly 1 record).
   - Deletion anomaly resolved (dropping enrollment preserves course and faculty).
3. **Comprehensive ER Diagram:**
   - Complete Mermaid ER diagram with primary keys (`[PK]`), foreign keys (`[FK]`), and cardinalities.

---

## ⚙️ Compilation and Execution
```bash
# Navigate to directory
cd "C:\Program Files\Java\jdk-21\bin\hcl\HCL-daily-tasks\HCL-Daily-Tasks\daily-tasks\day-18-relational-database-concepts\src"

# Compile simulator
javac -d ../bin NormalizationSimulator.java

# Run simulation
java -cp ../bin NormalizationSimulator
```
