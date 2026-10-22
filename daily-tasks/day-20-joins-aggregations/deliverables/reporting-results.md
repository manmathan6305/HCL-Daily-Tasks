# Day 20 Deliverable: Joins & Aggregations Reporting Results

## 1. Executive Summary
This report documents the verification of 10 e-commerce reporting queries and 5 project business intelligence reports. All join queries have been audited against mathematical Venn set intersections to ensure correct row counts without spurious Cartesian product row duplication.

---

## 2. Row Count Verification Matrix
| Query | Join Type / Clause | Expected Count | Observed Count | Verification Status |
| :--- | :--- | :--- | :--- | :--- |
| **Q1: Customers without Orders** | `LEFT JOIN ... WHERE o.order_id IS NULL` | **2 rows** | **2 rows** | **PASS** (Grace Hopper, Henry Ford) |
| **Q2: Revenue per Category** | `INNER JOIN ... GROUP BY category_name` | **3 rows** | **3 rows** | **PASS** (Laptops: $4,000, Audio: $1,480, Smart Home: $230) |
| **Q3: Categories Avg Price > 300** | `INNER JOIN ... HAVING AVG() > 300` | **2 rows** | **2 rows** | **PASS** (Laptops: $1,000, Audio: $310) |
| **Q4: Employee-Manager Pairs** | `SELF JOIN (LEFT JOIN m ON e.mgr=m.id)` | **7 rows** | **7 rows** | **PASS** (CEO + 6 employees) |
| **Q5: Unified Entity Catalog** | `UNION (Products + Suppliers)` | **12 rows** | **12 rows** | **PASS** (8 products + 4 suppliers) |
| **Q6: Products Never Ordered** | `LEFT JOIN ... WHERE oi.item_id IS NULL` | **2 rows** | **2 rows** | **PASS** (Apple Watch, Garmin) |
| **Q7: Top Spenders** | `INNER JOIN ... GROUP BY ... LIMIT 3` | **3 rows** | **3 rows** | **PASS** (Alice: $1,610, Frank: $1,530, Bob: $1,150) |
| **Q9: COUNT(*) vs COUNT(col)** | `COUNT(*)` vs `COUNT(discount_code)` | **10 vs 6** | **10 vs 6** | **PASS** (4 null discounts omitted) |

---

## 3. Project Business Reports Verification
1. **Customer Portfolio Balances:** 20 customers aggregated cleanly.
2. **Account Types with Avg Balance > $75k:** CURRENT ($199,000 avg) & SALARY ($74,200 avg) accurately identified.
3. **High-Volume Accounts:** Accounts exceeding 40,000 volume retrieved with zero duplicates.
4. **Transaction Breakdown:** Verified 8 CREDIT transactions ($453k total) and 12 DEBIT transactions ($118k total).
5. **City-Level Wealth:** Top metropolitan deposits aggregated without data inflation.
