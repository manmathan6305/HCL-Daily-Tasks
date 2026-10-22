-- =============================================================================
-- Project Database Schema: Smart Banking & Financial Ledger
-- Main Tables: customers, accounts, transactions (>= 20 rows per table)
-- =============================================================================

DROP TABLE IF EXISTS account_transactions;
DROP TABLE IF EXISTS bank_accounts;
DROP TABLE IF EXISTS bank_customers;

-- 1. Customers Table
CREATE TABLE bank_customers (
    customer_id VARCHAR(15) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    kyc_status VARCHAR(20) NOT NULL DEFAULT 'VERIFIED',
    city VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Bank Accounts Table
CREATE TABLE bank_accounts (
    account_number VARCHAR(20) PRIMARY KEY,
    customer_id VARCHAR(15) NOT NULL,
    account_type VARCHAR(20) NOT NULL, -- SAVINGS, CURRENT, SALARY
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    opened_date DATE NOT NULL,
    CONSTRAINT fk_acc_cust FOREIGN KEY (customer_id) 
        REFERENCES bank_customers(customer_id) ON DELETE RESTRICT,
    CONSTRAINT chk_acc_balance CHECK (balance >= 0.00)
);

-- 3. Transactions Table
CREATE TABLE account_transactions (
    transaction_id VARCHAR(25) PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL,
    txn_type VARCHAR(10) NOT NULL, -- DEBIT, CREDIT
    amount DECIMAL(12, 2) NOT NULL,
    balance_after DECIMAL(15, 2) NOT NULL,
    txn_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(200),
    CONSTRAINT fk_txn_acc FOREIGN KEY (account_number) 
        REFERENCES bank_accounts(account_number) ON DELETE CASCADE
);
