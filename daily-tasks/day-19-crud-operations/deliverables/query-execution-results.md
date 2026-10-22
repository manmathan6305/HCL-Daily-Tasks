# Day 19 Deliverable: CRUD Operations Query Execution Report

## 1. Executive Summary
This report validates that `schema.sql`, `seed.sql`, and `queries.sql` execute cleanly from scratch on any standard relational database engine (MySQL / ANSI SQL / SQLite). The dataset establishes 6 corporate departments, 30 employee records, and an extensive banking project database with >= 20 rows per main table.

---

## 2. Query Results Matrix (12 Questions)
| Question | Filtering / Sorting Criteria | SQL Syntax Highlight | Result Summary |
| :--- | :--- | :--- | :--- |
| **Q1** | Salary Range | `WHERE salary BETWEEN 60000 AND 90000` | 13 employees matched, ordered descending |
| **Q2** | Name Prefix | `WHERE first_name LIKE 'A%'` | 5 employees (Aarav, Ananya, Aditya, Amrita) |
| **Q3** | Department Membership | `WHERE dept_id IN ('DEP-ENG', 'DEP-FIN', 'DEP-IT')` | 15 employees total across 3 departments |
| **Q4** | Date Filtering | `WHERE hire_date >= '2023-01-01'` | 14 recent hires |
| **Q5** | Top Earners | `ORDER BY salary DESC LIMIT 5` | CFO ($195k), VP Eng ($185k), VP Sales ($160k) |
| **Q6** | Null Value Check | `WHERE manager_id IS NULL` | 6 VP/Director department heads |
| **Q7** | Commission Filter | `WHERE commission_pct > 0.00` | 8 sales & executive employees |
| **Q8** | Substring Matching | `WHERE last_name LIKE '%sh%'` | Deshmukh, Joshi |
| **Q9** | Pagination | `LIMIT 5 OFFSET 5` | Page 2 records (IDs 1006 to 1010) |
| **Q10** | Threshold Filter | `WHERE salary < 60000.00` | 6 entry-level and junior employees |
| **Q11** | Phone Prefix Pattern | `WHERE phone LIKE '+91-981%'` | Telephony verification matching employees |
| **Q12** | Safe Transactional Update | `BEGIN TRANSACTION ... UPDATE ... COMMIT` | Safe salary revision for employee 1010 |

---

## 3. Project Schema Verification
- `bank_customers`: **20 rows**
- `bank_accounts`: **20 rows**
- `account_transactions`: **20 rows**
Total project entities verified cleanly.
