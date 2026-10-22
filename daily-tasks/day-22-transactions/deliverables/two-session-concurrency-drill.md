# Day 22 Deliverable: Two-Session Concurrency Drill & Lost Update Fix

## 1. The Lost Update Problem (Two Sessions Without Locks)
When two transactions concurrently read the same account balance and modify it without locking, the later write unconditionally overwrites the earlier write:

```text
Time │ Session 1 (ATM Deposit $500)       │ Session 2 (Mobile Transfer $200)
─────┼────────────────────────────────────┼────────────────────────────────────
T1   │ BEGIN;                             │ BEGIN;
T2   │ SELECT balance FROM accounts       │
     │   WHERE id='ACC-A'; (Reads $1000)  │
T3   │                                    │ SELECT balance FROM accounts
     │                                    │   WHERE id='ACC-A'; (Reads $1000)
T4   │ UPDATE accounts SET balance = 1500 │
     │   WHERE id='ACC-A';                │
T5   │ COMMIT;                            │
T6   │                                    │ UPDATE accounts SET balance = 1200
     │                                    │   WHERE id='ACC-A';
T7   │                                    │ COMMIT;
─────┴────────────────────────────────────┴────────────────────────────────────
Outcome: Final balance is $1200. Session 1's $500 deposit is completely LOST!
```

---

## 2. The Solution: Pessimistic Row Locking (`SELECT ... FOR UPDATE`)

```text
Time │ Session 1 (Pessimistic Lock)       │ Session 2 (Blocked / Waiting)
─────┼────────────────────────────────────┼────────────────────────────────────
T1   │ BEGIN;                             │ BEGIN;
T2   │ SELECT balance FROM accounts       │
     │   WHERE id='ACC-A' FOR UPDATE;     │
     │   --> Acquired Exclusive Row Lock  │
T3   │                                    │ SELECT balance FROM accounts
     │                                    │   WHERE id='ACC-A' FOR UPDATE;
     │                                    │   --> BLOCKED (Waits for lock)
T4   │ UPDATE accounts SET balance = 1500 │       ...
     │   WHERE id='ACC-A';                │       ...
T5   │ COMMIT;                            │       ...
     │   --> Released Row Lock            │   --> Lock Granted!
T6   │                                    │   --> Reads updated balance $1500
T7   │                                    │ UPDATE accounts SET balance = 1700
     │                                    │   WHERE id='ACC-A';
T8   │                                    │ COMMIT;
─────┴────────────────────────────────────┴────────────────────────────────────
Outcome: Final balance is $1700.00. 100% Correct and Consistent!
```
