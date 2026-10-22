-- =============================================================================
-- HCL Day 22: Transactions - Bank Transfer Lab
-- =============================================================================

DROP TABLE IF EXISTS account_transactions;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    account_number VARCHAR(20) PRIMARY KEY,
    holder_name VARCHAR(100) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL,
    CONSTRAINT chk_positive_balance CHECK (balance >= 0.00)
);

CREATE TABLE account_transactions (
    txn_id VARCHAR(30) PRIMARY KEY,
    source_acc VARCHAR(20) NOT NULL,
    dest_acc VARCHAR(20) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seed Initial Accounts
INSERT INTO accounts (account_number, holder_name, balance) VALUES
('ACC-A', 'Alice Walker', 100000.00),
('ACC-B', 'Bob Miller',   50000.00);

-- =============================================================================
-- PART 1: SUCCESSFUL TRANSFER WITH COMMIT ($20,000 from ACC-A to ACC-B)
-- =============================================================================
BEGIN TRANSACTION;

-- Step 1: Debit Source Account
UPDATE accounts 
SET balance = balance - 20000.00 
WHERE account_number = 'ACC-A';

-- Step 2: Credit Destination Account
UPDATE accounts 
SET balance = balance + 20000.00 
WHERE account_number = 'ACC-B';

-- Step 3: Record Audit Entry
INSERT INTO account_transactions (txn_id, source_acc, dest_acc, amount, status)
VALUES ('TXN-SUCCESS-001', 'ACC-A', 'ACC-B', 20000.00, 'COMPLETED');

COMMIT;

-- Verify state after commit:
-- ACC-A: 80,000.00 | ACC-B: 70,000.00 | Total System Balance: 150,000.00 (PRESERVED)

-- =============================================================================
-- PART 2: FORCED FAILURE WITH ROLLBACK ($500,000 transfer exceeds balance)
-- =============================================================================
BEGIN TRANSACTION;

-- Attempt transfer exceeding balance
UPDATE accounts 
SET balance = balance - 500000.00 
WHERE account_number = 'ACC-A'; 
-- In MySQL/SQLite with CHECK constraint, this immediately throws:
-- CHECK constraint failed: chk_positive_balance

-- Catch block invokes ROLLBACK:
ROLLBACK;

-- Verify state after rollback:
-- ACC-A remains 80,000.00 | ACC-B remains 70,000.00 (Zero data loss, 100% Consistent!)

-- =============================================================================
-- PART 3: SAVEPOINTS DRILL
-- =============================================================================
BEGIN TRANSACTION;

UPDATE accounts SET balance = balance - 5000.00 WHERE account_number = 'ACC-A';
UPDATE accounts SET balance = balance + 5000.00 WHERE account_number = 'ACC-B';
SAVEPOINT sp_transfer_one;

-- Secondary operation that fails
UPDATE accounts SET balance = balance - 999999.00 WHERE account_number = 'ACC-A';
-- Failure detected: Roll back to savepoint
ROLLBACK TO sp_transfer_one;

COMMIT;

-- =============================================================================
-- PART 4: RESOLVING LOST UPDATES WITH SELECT ... FOR UPDATE (Pessimistic Lock)
-- =============================================================================
-- Session 1 Locks Row:
-- SELECT balance FROM accounts WHERE account_number = 'ACC-A' FOR UPDATE;
-- Session 2 attempts to read/update the same row -> Suspended until Session 1 commits!
