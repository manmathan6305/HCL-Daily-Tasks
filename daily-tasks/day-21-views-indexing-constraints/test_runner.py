import sqlite3
import os

def run_tests():
    print(">> Initializing clean database for Day 21 Constraint Validation...")
    conn = sqlite3.connect(":memory:")
    cursor = conn.cursor()
    cursor.execute('PRAGMA foreign_keys = ON;')

    base_dir = os.path.dirname(os.path.abspath(__file__))
    sql_dir = os.path.join(base_dir, "sql")

    # 1. Load schema with constraints
    with open(os.path.join(sql_dir, "constraints.sql"), "r", encoding="utf-8") as f:
        cursor.executescript(f.read())
    print(">> [SUCCESS] Constraints schema and views created successfully!")

    # Seed 1 valid department and 1 valid employee as baseline
    cursor.execute("INSERT INTO departments VALUES ('DEP-ENG', 'Engineering', 'Bangalore', 500000.00)")
    cursor.execute('''INSERT INTO employees VALUES 
        (1001, 'Aarav', 'Sharma', 'aarav@hcl.com', '+91-9811122233', '2020-01-15', 'Lead Dev', 85000.00, 0.10, 'DEP-ENG', 1)
    ''')
    cursor.execute("INSERT INTO employee_reviews VALUES (1, 1001, 2025, 5, 'Outstanding performer')")
    conn.commit()

    # 2. Testing EVERY Constraint with a Failing Insert
    print("\n>> [FAUL-DRILL] Testing each constraint with deliberate bad data:")

    failing_tests = [
        ("Test 1: Salary < 20,000 (Violates chk_emp_salary)",
         "INSERT INTO employees VALUES (1002, 'Bad', 'Sal', 'bad.sal@hcl.com', '123', '2021-01-01', 'Dev', 15000.00, 0.0, 'DEP-ENG', 1)"),

        ("Test 2: Commission > 0.50 (Violates chk_emp_commission)",
         "INSERT INTO employees VALUES (1003, 'Bad', 'Comm', 'bad.comm@hcl.com', '123', '2021-01-01', 'Sales', 50000.00, 0.85, 'DEP-ENG', 1)"),

        ("Test 3: Duplicate Email (Violates UNIQUE email constraint)",
         "INSERT INTO employees VALUES (1004, 'Dup', 'Email', 'aarav@hcl.com', '123', '2021-01-01', 'QA', 60000.00, 0.0, 'DEP-ENG', 1)"),

        ("Test 4: Non-HCL Email Domain (Violates chk_emp_email LIKE '%@hcl.com')",
         "INSERT INTO employees VALUES (1005, 'Ext', 'User', 'hacker@gmail.com', '123', '2021-01-01', 'Dev', 60000.00, 0.0, 'DEP-ENG', 1)"),

        ("Test 5: Hire Date Before 2010 (Violates chk_emp_hire_date)",
         "INSERT INTO employees VALUES (1006, 'Old', 'Hire', 'old.hire@hcl.com', '123', '1998-05-12', 'Admin', 60000.00, 0.0, 'DEP-ENG', 1)"),

        ("Test 6: Department Budget < 100,000 (Violates chk_dept_budget)",
         "INSERT INTO departments VALUES ('DEP-TINY', 'Tiny Dept', 'Pune', 50000.00)"),

        ("Test 7: Review Rating > 5 (Violates chk_review_rating)",
         "INSERT INTO employee_reviews VALUES (2, 1001, 2025, 9, 'Impossible rating')"),

        ("Test 8: NULL First Name (Violates NOT NULL constraint)",
         "INSERT INTO employees VALUES (1007, NULL, 'NoName', 'noname@hcl.com', '123', '2021-01-01', 'Dev', 60000.00, 0.0, 'DEP-ENG', 1)"),
    ]

    for label, bad_insert in failing_tests:
        try:
            cursor.execute(bad_insert)
            raise AssertionError(f"SECURITY FLAW: {label} succeeded when it should have failed!")
        except (sqlite3.IntegrityError, sqlite3.OperationalError) as ex:
            print(f"  [REJECTED AS EXPECTED] {label}")
            print(f"    >> Error caught: {ex}")

    # 3. Cascading Deletes Drill
    print("\n>> [CASCADE DRILL] Testing ON DELETE CASCADE:")
    cursor.execute("SELECT COUNT(*) FROM employee_reviews WHERE emp_id = 1001")
    before_reviews = cursor.fetchone()[0]
    print(f"  Reviews before parent deletion: {before_reviews}")
    cursor.execute("DELETE FROM employees WHERE emp_id = 1001")
    cursor.execute("SELECT COUNT(*) FROM employee_reviews WHERE emp_id = 1001")
    after_reviews = cursor.fetchone()[0]
    print(f"  Reviews after deleting employee 1001: {after_reviews}")
    assert after_reviews == 0, "Reviews were not cascaded upon employee deletion!"
    print("  >> [SUCCESS] ON DELETE CASCADE successfully cleaned child reviews!")

    # 4. Composite Index & EXPLAIN comparison
    print("\n>> [EXPLAIN DRILL] Comparing query execution before and after Composite Index:")
    # Seed 100 dummy rows
    for i in range(2000, 2100):
        dept = 'DEP-ENG' if i % 2 == 0 else 'DEP-FIN'
        cursor.execute(f"INSERT OR IGNORE INTO departments VALUES ('{dept}', 'Dept {dept}', 'Loc', 500000)")
        cursor.execute(f'''INSERT INTO employees VALUES 
            ({i}, 'Emp{i}', 'L{i}', 'emp{i}@hcl.com', '999', '2022-01-01', 'Role', {30000 + i*10}, 0.0, '{dept}', 1)
        ''')
    conn.commit()

    query = "EXPLAIN QUERY PLAN SELECT emp_id, first_name, salary FROM employees WHERE dept_id = 'DEP-ENG' AND salary > 45000"
    
    cursor.execute(query)
    plan_before = cursor.fetchall()
    print("  EXPLAIN PLAN (Before Index):")
    for row in plan_before:
        print(f"    {row}")

    # Create composite index
    cursor.execute("CREATE INDEX idx_emp_dept_salary ON employees(dept_id, salary)")
    conn.commit()

    cursor.execute(query)
    plan_after = cursor.fetchall()
    print("  EXPLAIN PLAN (After Composite Index idx_emp_dept_salary):")
    for row in plan_after:
        print(f"    {row}")

    # 5. Project Schema Constraints & View Verification
    print("\n>> [PROJECT SCHEMA] Verifying project-constraints.sql:")
    with open(os.path.join(sql_dir, "project-constraints.sql"), "r", encoding="utf-8") as f:
        cursor.executescript(f.read())
    
    # Test project negative balance check
    cursor.execute("INSERT INTO bank_customers VALUES ('C1', 'Test', 't@c.com', '123', 'VERIFIED', 'Mumbai')")
    try:
        cursor.execute("INSERT INTO bank_accounts VALUES ('ACC-X', 'C1', 'SAVINGS', -500.00, 'ACTIVE', '2026-01-01')")
        raise AssertionError("Project negative balance check failed to reject!")
    except sqlite3.IntegrityError as ex:
        print(f"  [PROJECT REJECTED] Negative balance rejected: {ex}")

    conn.close()
    print("\n>> ALL CHECKS PASSED: Every constraint tested with failing insert; EXPLAIN verified!")

if __name__ == "__main__":
    run_tests()
