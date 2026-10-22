# DDL vs DML Fundamentals

## 1. Classification of SQL Commands
| Category | Meaning | Commands | Transaction Behavior |
| :--- | :--- | :--- | :--- |
| **DDL (Data Definition Language)** | Defines the structure/schema of the database | `CREATE`, `ALTER`, `DROP`, `TRUNCATE` | Auto-commits implicitly in MySQL |
| **DML (Data Manipulation Language)** | Modifies or manipulates data rows | `INSERT`, `UPDATE`, `DELETE`, `MERGE` | Transactional; can be rolled back |
| **DQL (Data Query Language)** | Retrieves stored records | `SELECT` | Read-only |
| **TCL (Transaction Control Language)** | Manages transactions | `COMMIT`, `ROLLBACK`, `SAVEPOINT` | Controls persistence |

---

## 2. Core SQL Data Types
- `INT` / `BIGINT`: 4-byte / 8-byte whole numbers.
- `VARCHAR(n)`: Variable-length characters up to $n$.
- `DECIMAL(p, s)`: Exact fixed-point numeric representation for currency (avoids IEEE floating-point rounding errors).
- `DATE`: `YYYY-MM-DD`.
- `TIMESTAMP` / `DATETIME`: Date and time with microsecond precision.
- `BOOLEAN`: Logical `TRUE` / `FALSE` (aliased to `TINYINT(1)` in MySQL).
