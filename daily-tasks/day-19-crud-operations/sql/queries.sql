-- =============================================================================
-- HCL Day 19: CRUD Operations - 12 Filtering & Sorting Questions
-- =============================================================================

-- Question 1: Find all employees with salary BETWEEN 60000 and 90000
SELECT emp_id, first_name, last_name, job_title, salary, dept_id
FROM employees
WHERE salary BETWEEN 60000.00 AND 90000.00
ORDER BY salary DESC;

-- Question 2: Find all employees whose first name begins with 'A'
SELECT emp_id, first_name, last_name, email, dept_id
FROM employees
WHERE first_name LIKE 'A%'
ORDER BY first_name ASC;

-- Question 3: Find employees belonging to Engineering, Finance, or IT departments
SELECT emp_id, first_name, last_name, dept_id, salary
FROM employees
WHERE dept_id IN ('DEP-ENG', 'DEP-FIN', 'DEP-IT')
ORDER BY dept_id ASC, salary DESC;

-- Question 4: Find active employees hired in calendar year 2023 or later
SELECT emp_id, first_name, last_name, hire_date, job_title
FROM employees
WHERE hire_date >= '2023-01-01' AND is_active = TRUE
ORDER BY hire_date ASC;

-- Question 5: Top 5 highest earning employees in the company
SELECT emp_id, first_name, last_name, job_title, salary, dept_id
FROM employees
ORDER BY salary DESC
LIMIT 5;

-- Question 6: Find senior managers / directors (employees with NULL manager_id)
SELECT emp_id, first_name, last_name, job_title, salary, dept_id
FROM employees
WHERE manager_id IS NULL;

-- Question 7: Find employees who earn commission (commission_pct > 0)
SELECT emp_id, first_name, last_name, salary, commission_pct, (salary * commission_pct) AS est_commission
FROM employees
WHERE commission_pct > 0.00
ORDER BY commission_pct DESC;

-- Question 8: Find employees whose last name contains the substring 'sh'
SELECT emp_id, first_name, last_name, job_title, email
FROM employees
WHERE last_name LIKE '%sh%'
ORDER BY last_name ASC;

-- Question 9: Paginated employee roster (Page 2, 5 items per page)
SELECT emp_id, first_name, last_name, job_title, dept_id
FROM employees
ORDER BY emp_id ASC
LIMIT 5 OFFSET 5;

-- Question 10: Find employees with salaries strictly under 60000 sorted ascending
SELECT emp_id, first_name, last_name, job_title, salary, dept_id
FROM employees
WHERE salary < 60000.00
ORDER BY salary ASC;

-- Question 11: Find employees with phone numbers starting with standard prefix '+91-981'
SELECT emp_id, first_name, last_name, phone, dept_id
FROM employees
WHERE phone LIKE '+91-981%'
ORDER BY emp_id ASC;

-- Question 12: Practice Safe Updates in a Transaction
-- Step A: Verify target record before update
SELECT emp_id, first_name, last_name, salary, job_title
FROM employees
WHERE emp_id = 1010;

-- Step B: Safe transactional promotion with rollback test
BEGIN TRANSACTION;
UPDATE employees
SET salary = 50000.00, job_title = 'Associate Java Developer'
WHERE emp_id = 1010;

-- Step C: Check updated state within transaction
SELECT emp_id, first_name, last_name, salary, job_title
FROM employees
WHERE emp_id = 1010;

-- Commit permanent change
COMMIT;
