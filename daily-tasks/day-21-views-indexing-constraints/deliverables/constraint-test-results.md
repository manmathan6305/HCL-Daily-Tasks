# Day 21 Deliverable: Constraint Rejection Matrix

Every constraint was systematically subjected to an intentional bad-data insert to prove database-level validation:

| Test # | Constraint Under Test | Failing Data Injected | Result / Error Code | Status |
| :---: | :--- | :--- | :--- | :---: |
| **1** | `CHECK (salary >= 20000.00)` | `salary = 15000.00` | `CHECK constraint failed: chk_emp_salary` | **REJECTED (PASS)** |
| **2** | `CHECK (commission_pct BETWEEN 0 AND 0.5)` | `commission_pct = 0.85` | `CHECK constraint failed: chk_emp_commission` | **REJECTED (PASS)** |
| **3** | `UNIQUE (email)` | Duplicate `'aarav@hcl.com'` | `UNIQUE constraint failed: employees.email` | **REJECTED (PASS)** |
| **4** | `CHECK (email LIKE '%@hcl.com')` | `'hacker@gmail.com'` | `CHECK constraint failed: chk_emp_email` | **REJECTED (PASS)** |
| **5** | `CHECK (hire_date >= '2010-01-01')` | `hire_date = '1998-05-12'` | `CHECK constraint failed: chk_emp_hire_date` | **REJECTED (PASS)** |
| **6** | `CHECK (budget >= 100000.00)` | `budget = 50000.00` | `CHECK constraint failed: chk_dept_budget` | **REJECTED (PASS)** |
| **7** | `CHECK (rating BETWEEN 1 AND 5)` | `rating = 9` | `CHECK constraint failed: chk_review_rating` | **REJECTED (PASS)** |
| **8** | `NOT NULL (first_name)` | `first_name = NULL` | `NOT NULL constraint failed: employees.first_name` | **REJECTED (PASS)** |
| **9** | `CHECK (balance >= 0.00)` | `balance = -500.00` | `CHECK constraint failed: chk_acc_balance` | **REJECTED (PASS)** |
| **10**| `ON DELETE CASCADE` | Delete parent employee | Child reviews automatically removed (count -> 0) | **VERIFIED (PASS)** |
