# Safe Updates and Filtering Guide

## 1. The Danger of UPDATE & DELETE Without WHERE
Executing `UPDATE employees SET salary = salary * 1.10;` without a `WHERE` clause modifies **every single employee** in the company. In production, this can lead to catastrophic data corruption.

### Best Practices:
1. **Always wrap in transactions:**
   ```sql
   START TRANSACTION;
   UPDATE employees SET salary = 95000 WHERE emp_id = 1005;
   -- Inspect affected rows
   SELECT * FROM employees WHERE emp_id = 1005;
   COMMIT; -- or ROLLBACK if rows affected != 1
   ```
2. **Enable Safe Update Mode in MySQL:**
   ```sql
   SET SQL_SAFE_UPDATES = 1;
   ```
   Requires a key column in the `WHERE` clause.
