# Aggregations, GROUP BY, and HAVING

## 1. SQL Query Processing Order (Logical Execution)
1. `FROM` & `JOIN`: Gathers and combines tables.
2. `WHERE`: Filters individual rows **before** aggregation.
3. `GROUP BY`: Groups rows into summary buckets based on common values.
4. `HAVING`: Filters aggregated group results **after** grouping.
5. `SELECT`: Computes expressions, aliases, and aggregates.
6. `DISTINCT`: Eliminates duplicate rows.
7. `ORDER BY`: Sorts the final output.
8. `LIMIT` / `OFFSET`: Paginates results.

---

## 2. WHERE vs HAVING Rules
- `WHERE` cannot contain aggregate functions (`WHERE SUM(price) > 100` is illegal).
- `HAVING` filters group summaries created by `GROUP BY` (`HAVING AVG(salary) > 50000` is legal).
- `COUNT(*)` counts total rows including nulls; `COUNT(column)` only counts non-null occurrences of that column.
