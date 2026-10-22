-- =============================================================================
-- HCL Day 19: CRUD Operations - Database Schema
-- Dialect: MySQL 8.0+ / ANSI SQL
-- Description: Establishes enterprise departments and employees database
-- =============================================================================

DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

-- 1. Departments Table (DDL)
CREATE TABLE departments (
    dept_id VARCHAR(10) PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL UNIQUE,
    location VARCHAR(100) NOT NULL,
    budget DECIMAL(15, 2) NOT NULL DEFAULT 500000.00,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Employees Table (DDL)
CREATE TABLE employees (
    emp_id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    hire_date DATE NOT NULL,
    job_title VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    commission_pct DECIMAL(4, 2) DEFAULT 0.00,
    manager_id INT,
    dept_id VARCHAR(10) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_emp_dept FOREIGN KEY (dept_id) 
        REFERENCES departments(dept_id) ON DELETE RESTRICT,
    CONSTRAINT fk_emp_mgr FOREIGN KEY (manager_id) 
        REFERENCES employees(emp_id) ON DELETE SET NULL,
    CONSTRAINT chk_emp_salary CHECK (salary >= 20000.00)
);

CREATE INDEX idx_emp_dept ON employees(dept_id);
CREATE INDEX idx_emp_salary ON employees(salary);
CREATE INDEX idx_emp_hire_date ON employees(hire_date);
