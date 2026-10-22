# Database Constraints and Views Guide

## 1. Constraint Hierarchy
- **Entity Integrity:** `PRIMARY KEY` (Unique + Not Null).
- **Referential Integrity:** `FOREIGN KEY` (`ON DELETE CASCADE`, `ON DELETE RESTRICT`, `ON DELETE SET NULL`).
- **Domain Integrity:** `NOT NULL`, `DEFAULT`, `CHECK` expressions.
- **User-Defined Business Rules:** Custom regex, range thresholds.

---

## 2. The Power of Views
1. **Security & Data Masking:** Hide confidential columns (e.g., hiding `salary` in `v_public_employee_directory`).
2. **Simplified Queries:** Encapsulate complex multi-table joins and aggregations (`v_department_payroll_summary`).
