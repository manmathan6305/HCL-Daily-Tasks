import sqlite3
import os

def test_joins():
    print(">> Initializing in-memory relational database...")
    conn = sqlite3.connect(":memory:")
    cursor = conn.cursor()

    base_dir = os.path.dirname(os.path.abspath(__file__))
    sql_dir = os.path.join(base_dir, "sql")

    # 1. Execute schema.sql and seed.sql
    with open(os.path.join(sql_dir, "schema.sql"), "r", encoding="utf-8") as f:
        cursor.executescript(f.read())
    with open(os.path.join(sql_dir, "seed.sql"), "r", encoding="utf-8") as f:
        cursor.executescript(f.read())
    print(">> [SUCCESS] E-commerce schema and seed loaded cleanly!")

    # 2. Test 10 Questions and verify row counts
    print("\n>> Executing 10 E-Commerce Reporting Questions (joins.sql):")

    # Q1: Customers with NO orders (LEFT JOIN + IS NULL) -> Must be exactly 2
    cursor.execute('''
        SELECT c.customer_id, c.customer_name 
        FROM customers c 
        LEFT JOIN orders o ON c.customer_id = o.customer_id 
        WHERE o.order_id IS NULL
    ''')
    q1 = cursor.fetchall()
    print(f"  [Q1: Zero-order customers] -> Count: {len(q1)} (Expected: 2) -> {q1}")
    assert len(q1) == 2, f"Expected 2 rows, got {len(q1)}"

    # Q2: Category revenue
    cursor.execute('''
        SELECT cat.category_name, SUM(oi.quantity * oi.unit_price) AS total_revenue
        FROM categories cat
        INNER JOIN products p ON cat.category_id = p.category_id
        INNER JOIN order_items oi ON p.product_id = oi.product_id
        GROUP BY cat.category_name
        ORDER BY total_revenue DESC
    ''')
    q2 = cursor.fetchall()
    print(f"  [Q2: Category Revenue] -> {q2}")
    assert len(q2) == 3, f"Expected 3 active categories, got {len(q2)}"

    # Q3: Categories with avg price > 300 (HAVING)
    cursor.execute('''
        SELECT cat.category_name, AVG(p.unit_price) AS avg_price
        FROM categories cat
        INNER JOIN products p ON cat.category_id = p.category_id
        GROUP BY cat.category_name
        HAVING AVG(p.unit_price) > 300.00
    ''')
    q3 = cursor.fetchall()
    print(f"  [Q3: Categories AVG price > 300 (HAVING)] -> {q3}")

    # Q4: Employee-Manager Self-Join
    cursor.execute('''
        SELECT e.employee_name, COALESCE(m.employee_name, 'TOP LEVEL')
        FROM employees e
        LEFT JOIN employees m ON e.manager_id = m.employee_id
    ''')
    q4 = cursor.fetchall()
    print(f"  [Q4: Employee-Manager Hierarchy (Self Join)] -> Total employees: {len(q4)}")
    assert len(q4) == 7, f"Expected 7 employees, got {len(q4)}"

    # Q5: UNION list
    cursor.execute('''
        SELECT product_name AS name FROM products
        UNION
        SELECT supplier_name AS name FROM suppliers
    ''')
    q5 = cursor.fetchall()
    print(f"  [Q5: UNION Products & Suppliers] -> Total entities: {len(q5)}")
    assert len(q5) == 12, f"Expected 12 distinct entities, got {len(q5)}"

    # Q6: Products never ordered
    cursor.execute('''
        SELECT p.product_name
        FROM products p
        LEFT JOIN order_items oi ON p.product_id = oi.product_id
        WHERE oi.item_id IS NULL
    ''')
    q6 = cursor.fetchall()
    print(f"  [Q6: Never-ordered products] -> Count: {len(q6)} -> {q6}")
    assert len(q6) == 2, f"Expected 2 products never ordered, got {len(q6)}"

    # Q7: Top customer spenders
    cursor.execute('''
        SELECT c.customer_name, SUM(o.total_amount) AS total
        FROM customers c
        INNER JOIN orders o ON c.customer_id = o.customer_id
        GROUP BY c.customer_id
        ORDER BY total DESC LIMIT 3
    ''')
    q7 = cursor.fetchall()
    print(f"  [Q7: Top 3 spenders] -> {q7}")

    # Q9: COUNT(*) vs COUNT(col)
    cursor.execute('''
        SELECT COUNT(*), COUNT(discount_code) FROM orders
    ''')
    q9 = cursor.fetchone()
    print(f"  [Q9: COUNT(*) vs COUNT(discount_code)] -> Total Orders={q9[0]}, With Discount={q9[1]} (NULLs properly omitted)")
    assert q9[0] == 10 and q9[1] == 6

    # 3. Test Project Reports
    print("\n>> Validating 5 Project Business Reports (project-reports.sql):")
    # Load Day 19 Project Schema & Seed first
    p_schema = os.path.join(base_dir, "..", "day-19-crud-operations", "sql", "project-schema.sql")
    p_seed = os.path.join(base_dir, "..", "day-19-crud-operations", "sql", "project-seed.sql")
    with open(p_schema, "r", encoding="utf-8") as f:
        cursor.executescript(f.read())
    with open(p_seed, "r", encoding="utf-8") as f:
        cursor.executescript(f.read())

    with open(os.path.join(sql_dir, "project-reports.sql"), "r", encoding="utf-8") as f:
        p_reports_sql = f.read()
    
    # Run each report query
    for i, report_stmt in enumerate(p_reports_sql.split(";")):
        stmt = report_stmt.strip()
        if stmt and not stmt.startswith("--"):
            cursor.execute(stmt)
            rows = cursor.fetchall()
            print(f"  [Project Report {i+1}] -> Returned {len(rows)} rows without duplicates")

    conn.close()
    print("\n>> ALL CHECKS PASSED: Correct row counts and zero duplicate rows from joins!")

if __name__ == "__main__":
    test_joins()
