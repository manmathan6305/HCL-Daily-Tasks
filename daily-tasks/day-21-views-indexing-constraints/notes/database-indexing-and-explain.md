# Database Indexing & EXPLAIN Deep Dive

## 1. B-Tree Index Mechanics
- **Balanced Multi-way Tree:** Keeps data sorted and allows searches, sequential access, insertions, and deletions in logarithmic time ($O(\log N)$).
- **Index Leaf Pages:** Store indexed keys along with row pointers (ROWID / clustered PK).
- **Index Cost Trade-Off:** Speeds up `SELECT`, but imposes overhead on every `INSERT`, `UPDATE`, and `DELETE`.

---

## 2. EXPLAIN Plan Interpretation
- `SCAN TABLE`: Full table scan; examines every single row. Acceptable only for very small tables (< 500 rows).
- `SEARCH TABLE ... USING INDEX`: Targeted index lookup; navigates down the B-tree to locate matching records in microsecond intervals.
