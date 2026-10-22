-- =============================================================================
-- Critical Project Operation: High-Value Merchant Escrow Settlement
-- Concurrency-Sensitive Non-Functional Requirement (NFR)
-- =============================================================================

BEGIN TRANSACTION;

-- 1. Lock customer source account to prevent concurrent overdraft (Pessimistic Lock)
SELECT balance 
FROM bank_accounts 
WHERE account_number = 'ACC-1001' 
FOR UPDATE;

-- 2. Validate balance and debit
UPDATE bank_accounts 
SET balance = balance - 15000.00 
WHERE account_number = 'ACC-1001' AND balance >= 15000.00;

-- 3. Credit merchant settlement account
UPDATE bank_accounts 
SET balance = balance + 15000.00 
WHERE account_number = 'ACC-1002';

-- 4. Create immutable ledger audit record
INSERT INTO account_transactions (
    transaction_id, account_number, txn_type, amount, balance_after, description
) VALUES (
    'TXN-ESCROW-994', 'ACC-1001', 'DEBIT', 15000.00, 
    (SELECT balance FROM bank_accounts WHERE account_number = 'ACC-1001'),
    'Merchant Escrow Disbursement'
);

-- 5. Commit transaction permanently
COMMIT;
