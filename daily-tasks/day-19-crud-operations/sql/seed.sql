-- =============================================================================
-- HCL Day 19: CRUD Operations - Seed Data (Multi-Row Inserts)
-- 6 Departments and 30 Employees across diverse functions
-- =============================================================================

-- 1. Populate Departments (6 rows)
INSERT INTO departments (dept_id, dept_name, location, budget, is_active) VALUES
('DEP-ENG', 'Software Engineering', 'Bangalore Tech Park', 3500000.00, TRUE),
('DEP-FIN', 'Corporate Finance', 'Mumbai Financial District', 1800000.00, TRUE),
('DEP-HR',  'Human Resources', 'Chennai Campus', 950000.00, TRUE),
('DEP-SAL', 'Global Enterprise Sales', 'Noida Tower A', 2200000.00, TRUE),
('DEP-IT',  'Infrastructure & Cloud IT', 'Hyderabad SEZ', 1500000.00, TRUE),
('DEP-OPS', 'Supply Chain & Operations', 'Pune Hub', 1100000.00, TRUE);

-- 2. Populate Employees (30 rows, structured reporting hierarchy)
INSERT INTO employees (emp_id, first_name, last_name, email, phone, hire_date, job_title, salary, commission_pct, manager_id, dept_id, is_active) VALUES
-- Senior Leadership (Null manager_id)
(1001, 'Aarav', 'Sharma', 'aarav.sharma@hcl.com', '+91-9811122233', '2019-03-15', 'VP of Engineering', 185000.00, 0.15, NULL, 'DEP-ENG', TRUE),
(1002, 'Priya', 'Sundaram', 'priya.sundaram@hcl.com', '+91-9822233344', '2018-07-01', 'Chief Financial Officer', 195000.00, 0.10, NULL, 'DEP-FIN', TRUE),
(1003, 'Ananya', 'Deshmukh', 'ananya.deshmukh@hcl.com', '+91-9833344455', '2020-01-10', 'Director of HR', 140000.00, 0.05, NULL, 'DEP-HR', TRUE),

-- Engineering Department (DEP-ENG)
(1004, 'Aditya', 'Verma', 'aditya.verma@hcl.com', '+91-9844455566', '2021-02-01', 'Principal Architect', 135000.00, 0.08, 1001, 'DEP-ENG', TRUE),
(1005, 'Amrita', 'Sen', 'amrita.sen@hcl.com', '+91-9855566677', '2022-04-15', 'Senior Java Developer', 92000.00, 0.00, 1004, 'DEP-ENG', TRUE),
(1006, 'Bhavin', 'Patel', 'bhavin.patel@hcl.com', '+91-9866677788', '2022-09-01', 'Full Stack Engineer', 82000.00, 0.00, 1004, 'DEP-ENG', TRUE),
(1007, 'Chetan', 'Reddy', 'chetan.reddy@hcl.com', '+91-9877788899', '2023-01-15', 'Backend Engineer', 75000.00, 0.00, 1005, 'DEP-ENG', TRUE),
(1008, 'Divya', 'Nair', 'divya.nair@hcl.com', '+91-9888899900', '2023-06-01', 'QA Automation Lead', 78000.00, 0.00, 1004, 'DEP-ENG', TRUE),
(1009, 'Farhan', 'Khan', 'farhan.khan@hcl.com', '+91-9899900011', '2024-02-15', 'Junior Cloud Engineer', 55000.00, 0.00, 1004, 'DEP-ENG', TRUE),
(1010, 'Gayathri', 'Iyer', 'gayathri.iyer@hcl.com', '+91-9810111213', '2024-08-01', 'Graduate Trainee', 42000.00, 0.00, 1005, 'DEP-ENG', TRUE),

-- Corporate Finance (DEP-FIN)
(1011, 'Harish', 'Kulkarni', 'harish.kulkarni@hcl.com', '+91-9821222324', '2020-05-10', 'Senior Financial Analyst', 88000.00, 0.05, 1002, 'DEP-FIN', TRUE),
(1012, 'Ishaan', 'Joshi', 'ishaan.joshi@hcl.com', '+91-9832333435', '2021-11-20', 'Tax Specialist', 76000.00, 0.00, 1002, 'DEP-FIN', TRUE),
(1013, 'Jyoti', 'Chauhan', 'jyoti.chauhan@hcl.com', '+91-9843444546', '2023-03-01', 'Internal Auditor', 69000.00, 0.00, 1011, 'DEP-FIN', TRUE),
(1014, 'Kunal', 'Malhotra', 'kunal.malhotra@hcl.com', '+91-9854555657', '2024-01-15', 'Treasury Accountant', 58000.00, 0.00, 1011, 'DEP-FIN', TRUE),

-- Human Resources (DEP-HR)
(1015, 'Lavanya', 'Pillai', 'lavanya.pillai@hcl.com', '+91-9865666768', '2021-08-15', 'HR Business Partner', 72000.00, 0.00, 1003, 'DEP-HR', TRUE),
(1016, 'Manish', 'Tiwari', 'manish.tiwari@hcl.com', '+91-9876777879', '2022-12-01', 'Talent Acquisition Lead', 68000.00, 0.05, 1003, 'DEP-HR', TRUE),
(1017, 'Neha', 'Bansal', 'neha.bansal@hcl.com', '+91-9887888980', '2023-10-10', 'L&D Coordinator', 52000.00, 0.00, 1015, 'DEP-HR', TRUE),

-- Global Enterprise Sales (DEP-SAL)
(1018, 'Omkar', 'Bhide', 'omkar.bhide@hcl.com', '+91-9898999091', '2020-04-01', 'VP of Sales', 160000.00, 0.25, NULL, 'DEP-SAL', TRUE),
(1019, 'Pranav', 'Natarajan', 'pranav.n@hcl.com', '+91-9809111223', '2021-06-15', 'Enterprise Account Exec', 98000.00, 0.20, 1018, 'DEP-SAL', TRUE),
(1020, 'Rhea', 'Menon', 'rhea.menon@hcl.com', '+91-9819222334', '2022-02-01', 'Senior Sales Specialist', 89000.00, 0.18, 1018, 'DEP-SAL', TRUE),
(1021, 'Rohan', 'Saxena', 'rohan.saxena@hcl.com', '+91-9829333445', '2023-04-10', 'Inside Sales Representative', 54000.00, 0.12, 1019, 'DEP-SAL', TRUE),
(1022, 'Sanya', 'Mirza', 'sanya.mirza@hcl.com', '+91-9839444556', '2024-03-01', 'Pre-Sales Solutions Engineer', 77000.00, 0.10, 1019, 'DEP-SAL', TRUE),

-- Infrastructure & Cloud IT (DEP-IT)
(1023, 'Siddharth', 'Kaul', 'siddharth.k@hcl.com', '+91-9849555667', '2020-09-15', 'Head of Cloud Ops', 130000.00, 0.05, NULL, 'DEP-IT', TRUE),
(1024, 'Tanvi', 'Agarwal', 'tanvi.agarwal@hcl.com', '+91-9859666778', '2022-03-01', 'Senior DevOps Engineer', 94000.00, 0.00, 1023, 'DEP-IT', TRUE),
(1025, 'Uday', 'Shekhar', 'uday.shekhar@hcl.com', '+91-9869777889', '2023-05-15', 'Site Reliability Engineer', 85000.00, 0.00, 1023, 'DEP-IT', TRUE),
(1026, 'Varun', 'Nambiar', 'varun.n@hcl.com', '+91-9879888990', '2024-04-01', 'Cloud Security Analyst', 76000.00, 0.00, 1024, 'DEP-IT', TRUE),

-- Supply Chain & Operations (DEP-OPS)
(1027, 'Vidya', 'Swaminathan', 'vidya.s@hcl.com', '+91-9889999001', '2019-11-01', 'Director of Operations', 145000.00, 0.05, NULL, 'DEP-OPS', TRUE),
(1028, 'Waseem', 'Akram', 'waseem.akram@hcl.com', '+91-9890111212', '2021-10-15', 'Procurement Manager', 86000.00, 0.00, 1027, 'DEP-OPS', TRUE),
(1029, 'Yash', 'Bhardwaj', 'yash.bhardwaj@hcl.com', '+91-9801222323', '2023-08-01', 'Logistics Analyst', 62000.00, 0.00, 1028, 'DEP-OPS', TRUE),
(1030, 'Zara', 'Hussain', 'zara.hussain@hcl.com', '+91-9812333434', '2024-05-15', 'Operations Coordinator', 48000.00, 0.00, 1028, 'DEP-OPS', TRUE);
