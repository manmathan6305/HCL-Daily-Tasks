# Pessimistic vs Optimistic Locking

## 1. Pessimistic Locking
- **Strategy:** Assume conflicts are likely. Lock the resource before reading.
- **Implementation:** `SELECT ... FOR UPDATE` (Exclusive Lock) or `LOCK IN SHARE MODE` (Shared Lock).
- **Best For:** Heavy write contention, financial account balances, inventory reservation.

---

## 2. Optimistic Locking
- **Strategy:** Assume conflicts are rare. Allow concurrent reads without locking; verify version number before commit.
- **Implementation:** `WHERE id = ? AND version = current_version`
- **Best For:** Read-heavy web systems with low write collisions.
