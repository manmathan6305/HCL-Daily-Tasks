-- =============================================================================
-- Project Database: Business-Rule Constraints, 2 Justified Indexes & 1 View
-- =============================================================================

DROP TABLE IF EXISTS account_transactions;
DROP TABLE IF EXISTS bank_accounts;
DROP TABLE IF EXISTS bank_customers;
DROP VIEW IF EXISTS v_customer_portfolio_overview;

CREATE TABLE bank_customers (
    customer_id VARCHAR(15) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    kyc_status VARCHAR(20) NOT NULL DEFAULT 'VERIFIED',
    city VARCHAR(50) NOT NULL,
    CONSTRAINT chk_cust_kyc CHECK (kyc_status IN ('PENDING', 'VERIFIED', 'REJECTED')),
    CONSTRAINT chk_cust_email CHECK (email LIKE '%@%')
);

CREATE TABLE bank_accounts (
    account_number VARCHAR(20) PRIMARY KEY,
    customer_id VARCHAR(15) NOT NULL,
    account_type VARCHAR(20) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    opened_date DATE NOT NULL,
    CONSTRAINT fk_acc_cust FOREIGN KEY (customer_id) 
        REFERENCES bank_customers(customer_id) ON DELETE RESTRICT,
    CONSTRAINT chk_acc_balance CHECK (balance >= 0.00),
    CONSTRAINT chk_acc_type CHECK (account_type IN ('SAVINGS', 'CURRENT', 'SALARY')),
    CONSTRAINT chk_acc_status CHECK (status IN ('ACTIVE', 'DORMANT', 'FROZEN', 'CLOSED'))
);

CREATE TABLE account_transactions (
    transaction_id VARCHAR(25) PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL,
    txn_type VARCHAR(10) NOT NULL,
    amount DECIMAL(12, 2) NOT NULL,
    balance_after DECIMAL(15, 2) NOT NULL,
    txn_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(200),
    CONSTRAINT fk_txn_acc FOREIGN KEY (account_number) 
        REFERENCES bank_accounts(account_number) ON DELETE CASCADE,
    CONSTRAINT chk_txn_amount CHECK (amount > 0.00),
    CONSTRAINT chk_txn_type CHECK (txn_type IN ('CREDIT', 'DEBIT'))
);

-- Index 1: Composite index for fast account statement retrieval ordered by time
-- Justification: Bank customers frequently query recent transactions for their account
CREATE INDEX idx_txn_account_time ON account_transactions(account_number, txn_timestamp DESC);

-- Index 2: Composite index for compliance and KYC filtering by jurisdiction
-- Justification: Anti-Money Laundering (AML) queries filter by city and KYC status
CREATE INDEX idx_cust_city_kyc ON bank_customers(city, kyc_status);

-- Reporting View: Comprehensive Portfolio Overview
CREATE VIEW v_customer_portfolio_overview AS
SELECT c.customer_id, c.full_name, c.email, c.city, c.kyc_status,
       COUNT(a.account_number) AS active_accounts,
       COALESCE(SUM(a.balance), 0.00) AS total_balance
FROM bank_customers c
LEFT JOIN bank_accounts a ON c.customer_id = a.customer_id AND a.status = 'ACTIVE'
GROUP BY c.customer_id, c.full_name, c.email, c.city, c.kyc_status;
