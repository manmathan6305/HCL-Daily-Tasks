-- =============================================================================
-- HCL Day 21: Views, Indexing & Constraints
-- =============================================================================

DROP TABLE IF EXISTS employee_reviews;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;
DROP VIEW IF EXISTS v_department_payroll_summary;
DROP VIEW IF EXISTS v_public_employee_directory;

-- 1. Departments Table with Constraints
CREATE TABLE departments (
    dept_id VARCHAR(10) PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL UNIQUE,
    location VARCHAR(100) NOT NULL,
    budget DECIMAL(15, 2) NOT NULL DEFAULT 500000.00,
    CONSTRAINT chk_dept_budget CHECK (budget >= 100000.00)
);

-- 2. Employees Table with Comprehensive Business Constraints
CREATE TABLE employees (
    emp_id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    hire_date DATE NOT NULL,
    job_title VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    commission_pct DECIMAL(4, 2) NOT NULL DEFAULT 0.00,
    dept_id VARCHAR(10) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_emp_dept FOREIGN KEY (dept_id) 
        REFERENCES departments(dept_id) ON DELETE RESTRICT,
    CONSTRAINT chk_emp_salary CHECK (salary >= 20000.00),
    CONSTRAINT chk_emp_commission CHECK (commission_pct BETWEEN 0.00 AND 0.50),
    CONSTRAINT chk_emp_email CHECK (email LIKE '%@hcl.com'),
    CONSTRAINT chk_emp_hire_date CHECK (hire_date >= '2010-01-01')
);

-- 3. Dependent Reviews Table with ON DELETE CASCADE
CREATE TABLE employee_reviews (
    review_id INT PRIMARY KEY,
    emp_id INT NOT NULL,
    review_year INT NOT NULL,
    rating INT NOT NULL,
    review_notes TEXT,
    CONSTRAINT fk_rev_emp FOREIGN KEY (emp_id) 
        REFERENCES employees(emp_id) ON DELETE CASCADE,
    CONSTRAINT chk_review_rating CHECK (rating BETWEEN 1 AND 5)
);

-- =============================================================================
-- 4. Reporting & Security Views
-- =============================================================================

-- View A: Department Payroll & Staffing Summary
CREATE VIEW v_department_payroll_summary AS
SELECT d.dept_id, d.dept_name, d.location,
       COUNT(e.emp_id) AS total_employees,
       COALESCE(SUM(e.salary), 0.00) AS total_payroll,
       COALESCE(AVG(e.salary), 0.00) AS avg_salary,
       COALESCE(MAX(e.salary), 0.00) AS max_salary
FROM departments d
LEFT JOIN employees e ON d.dept_id = e.dept_id
GROUP BY d.dept_id, d.dept_name, d.location;

-- View B: Secure Public Employee Directory (Hides sensitive compensation data)
CREATE VIEW v_public_employee_directory AS
SELECT e.emp_id, e.first_name, e.last_name, e.email, e.job_title,
       d.dept_name, d.location
FROM employees e
INNER JOIN departments d ON e.dept_id = d.dept_id
WHERE e.is_active = TRUE;
