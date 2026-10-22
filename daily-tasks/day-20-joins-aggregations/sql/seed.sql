-- =============================================================================
-- HCL Day 20: Joins & Aggregations - Seed Data
-- Deterministic records to test outer joins, zero-order customers, self-joins, and aggregations
-- =============================================================================

-- 1. Customers (8 customers: 6 have orders, 2 have ZERO orders for LEFT JOIN test)
INSERT INTO customers (customer_id, customer_name, email, city) VALUES
(1, 'Alice Walker', 'alice@gmail.com', 'Bangalore'),
(2, 'Bob Miller', 'bob@gmail.com', 'Mumbai'),
(3, 'Carol Davis', 'carol@gmail.com', 'Delhi'),
(4, 'David Evans', 'david@gmail.com', 'Hyderabad'),
(5, 'Emily Clark', 'emily@gmail.com', 'Chennai'),
(6, 'Frank Wright', 'frank@gmail.com', 'Pune'),
(7, 'Grace Hopper', 'grace.noorders@gmail.com', 'Kolkata'), -- ZERO ORDERS
(8, 'Henry Ford', 'henry.noorders@gmail.com', 'Ahmedabad');   -- ZERO ORDERS

-- 2. Categories (4 categories)
INSERT INTO categories (category_id, category_name) VALUES
(101, 'Laptops & Computers'),
(102, 'Audio & Accessories'),
(103, 'Smart Home Devices'),
(104, 'Wearable Tech');

-- 3. Products (8 products: 6 ordered, 2 NEVER ordered to test outer joins)
INSERT INTO products (product_id, product_name, category_id, unit_price) VALUES
(201, 'Dell Latitude 5420', 101, 850.00),
(202, 'MacBook Air M2', 101, 1150.00),
(203, 'Sony WH-1000XM5', 102, 380.00),
(204, 'AirPods Pro 2', 102, 240.00),
(205, 'Amazon Echo Dot 5', 103, 50.00),
(206, 'Google Nest Thermostat', 103, 130.00),
(207, 'Apple Watch Ultra 2', 104, 799.00),     -- NEVER ORDERED
(208, 'Garmin Forerunner 965', 104, 599.00);    -- NEVER ORDERED

-- 4. Orders (10 orders; some with discount codes, some without)
INSERT INTO orders (order_id, customer_id, order_date, total_amount, discount_code) VALUES
(501, 1, '2026-09-01', 1230.00, 'SAVE10'),
(502, 1, '2026-09-15', 380.00,  NULL),
(503, 2, '2026-09-18', 1150.00, 'VIPGOLD'),
(504, 3, '2026-09-20', 240.00,  NULL),
(505, 3, '2026-09-25', 180.00,  'SAVE10'),
(506, 4, '2026-10-01', 850.00,  'FESTIVE'),
(507, 4, '2026-10-05', 50.00,   NULL),
(508, 5, '2026-10-10', 480.00,  'SAVE10'),
(509, 6, '2026-10-12', 380.00,  NULL),
(510, 6, '2026-10-14', 1150.00, 'VIPGOLD');

-- 5. Order Items
INSERT INTO order_items (item_id, order_id, product_id, quantity, unit_price) VALUES
(1, 501, 201, 1, 850.00),
(2, 501, 203, 1, 380.00),
(3, 502, 203, 1, 380.00),
(4, 503, 202, 1, 1150.00),
(5, 504, 204, 1, 240.00),
(6, 505, 205, 1, 50.00),
(7, 505, 206, 1, 130.00),
(8, 506, 201, 1, 850.00),
(9, 507, 205, 1, 50.00),
(10, 508, 204, 2, 240.00),
(11, 509, 203, 1, 380.00),
(12, 510, 202, 1, 1150.00);

-- 6. Employees (Hierarchical reporting structure for Self-Join)
INSERT INTO employees (employee_id, employee_name, manager_id, role) VALUES
(10, 'Aarav Sharma', NULL, 'Chief Executive Officer'),
(11, 'Bhavna Patel', 10,   'VP of Engineering'),
(12, 'Chirag Reddy', 10,   'VP of Product'),
(13, 'Deepak Verma', 11,   'Engineering Lead'),
(14, 'Esha Iyer',    11,   'Senior Backend Dev'),
(15, 'Farhan Khan',  12,   'Senior Product Manager'),
(16, 'Geeta Nair',   13,   'Junior Software Dev');

-- 7. Suppliers (For UNION testing)
INSERT INTO suppliers (supplier_id, supplier_name, contact_city) VALUES
(301, 'Dell Global Manufacturing', 'Bangalore'),
(302, 'Apple Logistics Asia', 'Mumbai'),
(303, 'Sony Electronics Hub', 'Tokyo'),
(304, 'Amazon Fulfillment Central', 'Hyderabad');
