# SQL Joins Visual & Conceptual Guide

## 1. Venn Diagram Intuition
```text
      Table A (Customers)             Table B (Orders)
       ┌───────────┐                 ┌───────────┐
       │     A     │   A ∩ B (INNER) │     B     │
       │           │      [Both]     │           │
       │ [A Only]  ├─────────────────┤  [B Only] │
       │           │                 │           │
       └───────────┘                 └───────────┘
```
- **INNER JOIN:** Returns only matching rows where join predicate evaluates to `TRUE`.
- **LEFT JOIN:** Returns all rows from left table $A$, with matched columns from $B$ or `NULL` if no match.
- **RIGHT JOIN:** Symmetric opposite of `LEFT JOIN`.
- **FULL OUTER JOIN:** Returns union of $A$ and $B$, inserting `NULL` for missing sides. Emulated in MySQL with `LEFT JOIN UNION RIGHT JOIN`.

---

## 2. The Danger of Cartesian Product Row Inflation
If joining a table with $M$ records to another table with $N$ records on a non-unique column without a proper primary-key/foreign-key relationship, the result set inflates to $M \times N$ rows.
- **Fix:** Always verify foreign key cardinality and use `COUNT(DISTINCT entity_id)` when aggregating across multi-level joins.
