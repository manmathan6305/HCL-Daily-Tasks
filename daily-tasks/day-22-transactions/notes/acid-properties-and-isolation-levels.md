# ACID Properties and Transaction Isolation Levels

## 1. ACID Definition
- **Atomicity:** All operations in a transaction succeed together or fail together (All or Nothing).
- **Consistency:** Database transitions only between valid states satisfying all constraints.
- **Isolation:** Concurrent transactions execute without mutual interference.
- **Durability:** Once committed, changes survive server crashes and power loss.

---

## 2. ANSI SQL Transaction Isolation Levels
| Isolation Level | Dirty Read | Non-Repeatable Read | Phantom Read |
| :--- | :---: | :---: | :---: |
| **READ UNCOMMITTED** | Permitted | Permitted | Permitted |
| **READ COMMITTED**   | Prevented | Permitted | Permitted |
| **REPEATABLE READ**  | Prevented | Prevented | Permitted (Prevented in InnoDB via MVCC) |
| **SERIALIZABLE**     | Prevented | Prevented | Prevented |
