# Day 21 Deliverable: EXPLAIN Query Plan Analysis Report

## 1. Context & Optimization Objective
Target Query:
```sql
SELECT emp_id, first_name, salary 
FROM employees 
WHERE dept_id = 'DEP-ENG' AND salary > 45000;
```

---

## 2. Before vs After Composite Index Execution Plan

| Metric | Before Index | After Composite Index (`idx_emp_dept_salary`) |
| :--- | :--- | :--- |
| **Index Used** | `NONE` (Table Scan) | `idx_emp_dept_salary (dept_id, salary)` |
| **Execution Plan** | `SCAN TABLE employees` | `SEARCH TABLE employees USING INDEX idx_emp_dept_salary (dept_id=? AND salary>?)` |
| **Complexity** | $O(N)$ (inspects all $N$ records) | $O(\log N + K)$ (B-tree root-to-leaf binary search) |
| **Disk Page I/O** | Scans entire table pages | Reads only matching index leaf pages |

---

## 3. Left-Most Prefix Rule Justification
The composite index is defined on `(dept_id, salary)`:
1. `WHERE dept_id = 'DEP-ENG' AND salary > 45000` -> **Fully Indexed** (Uses both columns).
2. `WHERE dept_id = 'DEP-ENG'` -> **Indexed** (Uses left-most prefix column `dept_id`).
3. `WHERE salary > 45000` -> **Index Skipped** (Cannot use B-tree if leading column `dept_id` is omitted).
