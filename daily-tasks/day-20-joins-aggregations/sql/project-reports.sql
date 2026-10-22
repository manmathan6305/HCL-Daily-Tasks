-- =============================================================================
-- Project Business Reports: Smart Banking System
-- 5 Analytical reports utilizing JOIN, GROUP BY, and HAVING
-- =============================================================================

-- Report 1: Total balance and active account count per customer
SELECT c.customer_id, c.full_name, c.city,
       COUNT(a.account_number) AS total_accounts,
       SUM(a.balance) AS combined_portfolio_balance
FROM bank_customers c
INNER JOIN bank_accounts a ON c.customer_id = a.customer_id
GROUP BY c.customer_id, c.full_name, c.city
ORDER BY combined_portfolio_balance DESC;

-- Report 2: Account types with average balance exceeding 75,000 (HAVING clause)
SELECT a.account_type,
       COUNT(a.account_number) AS account_count,
       AVG(a.balance) AS avg_balance,
       SUM(a.balance) AS aggregate_holdings
FROM bank_accounts a
GROUP BY a.account_type
HAVING AVG(a.balance) > 75000.00
ORDER BY aggregate_holdings DESC;

-- Report 3: High-volume transaction accounts (Accounts with transactions exceeding 40,000 total)
SELECT a.account_number, c.full_name, a.account_type,
       COUNT(t.transaction_id) AS txn_count,
       SUM(t.amount) AS total_volume
FROM bank_accounts a
INNER JOIN bank_customers c ON a.customer_id = c.customer_id
INNER JOIN account_transactions t ON a.account_number = t.account_number
GROUP BY a.account_number, c.full_name, a.account_type
HAVING SUM(t.amount) > 40000.00
ORDER BY total_volume DESC;

-- Report 4: Transaction breakdown by type (CREDIT vs DEBIT) with average transaction size
SELECT t.txn_type,
       COUNT(t.transaction_id) AS total_count,
       MIN(t.amount) AS min_amount,
       AVG(t.amount) AS avg_amount,
       MAX(t.amount) AS max_amount,
       SUM(t.amount) AS gross_throughput
FROM account_transactions t
GROUP BY t.txn_type;

-- Report 5: City-level banking penetration (Customer count, total accounts, total city wealth)
SELECT c.city,
       COUNT(DISTINCT c.customer_id) AS distinct_customers,
       COUNT(a.account_number) AS total_accounts,
       SUM(a.balance) AS total_city_wealth
FROM bank_customers c
INNER JOIN bank_accounts a ON c.customer_id = a.customer_id
GROUP BY c.city
HAVING SUM(a.balance) > 100000.00
ORDER BY total_city_wealth DESC;
