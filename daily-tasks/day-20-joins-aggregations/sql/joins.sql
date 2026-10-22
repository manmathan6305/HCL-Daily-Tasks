-- =============================================================================
-- HCL Day 20: Joins & Aggregations - 10 Core Reporting Questions
-- =============================================================================

-- Question 1: Customers who have NEVER placed an order (LEFT JOIN + IS NULL)
-- Expected count: 2 (Grace Hopper, Henry Ford)
SELECT c.customer_id, c.customer_name, c.email, c.city
FROM customers c
LEFT JOIN orders o ON c.customer_id = o.customer_id
WHERE o.order_id IS NULL;

-- Question 2: Total revenue generated per product category (INNER JOIN + SUM)
SELECT cat.category_name, 
       COUNT(DISTINCT oi.order_id) AS distinct_orders,
       SUM(oi.quantity * oi.unit_price) AS total_revenue
FROM categories cat
INNER JOIN products p ON cat.category_id = p.category_id
INNER JOIN order_items oi ON p.product_id = oi.product_id
GROUP BY cat.category_name
ORDER BY total_revenue DESC;

-- Question 3: Categories with average item price > $300 (GROUP BY + HAVING)
SELECT cat.category_name, 
       COUNT(p.product_id) AS product_count,
       AVG(p.unit_price) AS avg_product_price
FROM categories cat
INNER JOIN products p ON cat.category_id = p.category_id
GROUP BY cat.category_name
HAVING AVG(p.unit_price) > 300.00
ORDER BY avg_product_price DESC;

-- Question 4: Employee-Manager reporting hierarchy (SELF JOIN)
SELECT e.employee_name AS employee,
       e.role AS employee_role,
       COALESCE(m.employee_name, '<< TOP LEVEL BOARD >>') AS reports_to
FROM employees e
LEFT JOIN employees m ON e.manager_id = m.employee_id
ORDER BY e.employee_id ASC;

-- Question 5: Full unified list of entity names and cities (UNION)
SELECT product_name AS entity_name, 'PRODUCT' AS entity_type, 'N/A' AS location
FROM products
UNION
SELECT supplier_name AS entity_name, 'SUPPLIER' AS entity_type, contact_city AS location
FROM suppliers
ORDER BY entity_type DESC, entity_name ASC;

-- Question 6: Products that have NEVER been ordered (LEFT JOIN + IS NULL)
SELECT p.product_id, p.product_name, cat.category_name, p.unit_price
FROM products p
INNER JOIN categories cat ON p.category_id = cat.category_id
LEFT JOIN order_items oi ON p.product_id = oi.product_id
WHERE oi.item_id IS NULL;

-- Question 7: Top 3 customer spenders with total spend and order count
SELECT c.customer_name, c.city,
       COUNT(o.order_id) AS total_orders,
       SUM(o.total_amount) AS total_spent
FROM customers c
INNER JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.customer_name, c.city
ORDER BY total_spent DESC
LIMIT 3;

-- Question 8: Full Outer Join emulation in MySQL/SQLite using UNION
SELECT c.customer_name, o.order_id, o.total_amount
FROM customers c
LEFT JOIN orders o ON c.customer_id = o.customer_id
UNION
SELECT c.customer_name, o.order_id, o.total_amount
FROM orders o
LEFT JOIN customers c ON o.customer_id = c.customer_id;

-- Question 9: Difference between COUNT(*) and COUNT(discount_code)
-- Demonstrates COUNT(col) excludes NULL values while COUNT(*) counts rows
SELECT COUNT(*) AS total_orders,
       COUNT(discount_code) AS orders_with_discount,
       COUNT(*) - COUNT(discount_code) AS orders_without_discount
FROM orders;

-- Question 10: Multi-table join order summary breakdown
SELECT o.order_id, c.customer_name, p.product_name, oi.quantity, oi.unit_price, (oi.quantity * oi.unit_price) AS line_total
FROM orders o
INNER JOIN customers c ON o.customer_id = c.customer_id
INNER JOIN order_items oi ON o.order_id = oi.order_id
INNER JOIN products p ON oi.product_id = p.product_id
ORDER BY o.order_id ASC, oi.item_id ASC;
