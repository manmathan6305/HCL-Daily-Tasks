-- =============================================================================
-- Project Database Seed Data: >= 20 rows per main table
-- =============================================================================

-- 20 Customers
INSERT INTO bank_customers (customer_id, full_name, email, phone, kyc_status, city) VALUES
('CUST-001', 'Aarav Mehta', 'aarav.m@client.com', '+91-910001', 'VERIFIED', 'Mumbai'),
('CUST-002', 'Bhavna Sen', 'bhavna.s@client.com', '+91-910002', 'VERIFIED', 'Kolkata'),
('CUST-003', 'Chirag Reddy', 'chirag.r@client.com', '+91-910003', 'VERIFIED', 'Hyderabad'),
('CUST-004', 'Deepika Rao', 'deepika.r@client.com', '+91-910004', 'VERIFIED', 'Bangalore'),
('CUST-005', 'Eshan Nair', 'eshan.n@client.com', '+91-910005', 'VERIFIED', 'Kochi'),
('CUST-006', 'Farah Sheikh', 'farah.s@client.com', '+91-910006', 'VERIFIED', 'Delhi'),
('CUST-007', 'Gaurav Jain', 'gaurav.j@client.com', '+91-910007', 'VERIFIED', 'Jaipur'),
('CUST-008', 'Harini Iyer', 'harini.i@client.com', '+91-910008', 'VERIFIED', 'Chennai'),
('CUST-009', 'Inderjeet Singh', 'inder.s@client.com', '+91-910009', 'VERIFIED', 'Chandigarh'),
('CUST-010', 'Jaya Bhatt', 'jaya.b@client.com', '+91-910010', 'VERIFIED', 'Ahmedabad'),
('CUST-011', 'Karan Johar', 'karan.j@client.com', '+91-910011', 'VERIFIED', 'Mumbai'),
('CUST-012', 'Leela Nair', 'leela.n@client.com', '+91-910012', 'VERIFIED', 'Pune'),
('CUST-013', 'Manav Gupta', 'manav.g@client.com', '+91-910013', 'VERIFIED', 'Lucknow'),
('CUST-014', 'Nandini Das', 'nandini.d@client.com', '+91-910014', 'VERIFIED', 'Bhubaneswar'),
('CUST-015', 'Omkar Pillai', 'omkar.p@client.com', '+91-910015', 'VERIFIED', 'Coimbatore'),
('CUST-016', 'Pooja Hegde', 'pooja.h@client.com', '+91-910016', 'VERIFIED', 'Mangalore'),
('CUST-017', 'Qasim Ali', 'qasim.a@client.com', '+91-910017', 'VERIFIED', 'Bhopal'),
('CUST-018', 'Ritu Varma', 'ritu.v@client.com', '+91-910018', 'VERIFIED', 'Indore'),
('CUST-019', 'Sahil Kulkarni', 'sahil.k@client.com', '+91-910019', 'VERIFIED', 'Nagpur'),
('CUST-020', 'Tanya Roy', 'tanya.r@client.com', '+91-910020', 'VERIFIED', 'Guwahati');

-- 20 Bank Accounts
INSERT INTO bank_accounts (account_number, customer_id, account_type, balance, status, opened_date) VALUES
('ACC-1001', 'CUST-001', 'SAVINGS', 45000.00, 'ACTIVE', '2023-01-10'),
('ACC-1002', 'CUST-002', 'CURRENT', 125000.00, 'ACTIVE', '2023-01-15'),
('ACC-1003', 'CUST-003', 'SALARY', 68000.00, 'ACTIVE', '2023-02-01'),
('ACC-1004', 'CUST-004', 'SAVINGS', 32000.00, 'ACTIVE', '2023-02-12'),
('ACC-1005', 'CUST-005', 'SAVINGS', 89000.00, 'ACTIVE', '2023-03-05'),
('ACC-1006', 'CUST-006', 'CURRENT', 210000.00, 'ACTIVE', '2023-03-20'),
('ACC-1007', 'CUST-007', 'SALARY', 54000.00, 'ACTIVE', '2023-04-01'),
('ACC-1008', 'CUST-008', 'SAVINGS', 96000.00, 'ACTIVE', '2023-04-18'),
('ACC-1009', 'CUST-009', 'CURRENT', 175000.00, 'ACTIVE', '2023-05-10'),
('ACC-1010', 'CUST-010', 'SAVINGS', 41000.00, 'ACTIVE', '2023-05-25'),
('ACC-1011', 'CUST-011', 'SALARY', 73000.00, 'ACTIVE', '2023-06-01'),
('ACC-1012', 'CUST-012', 'SAVINGS', 29000.00, 'ACTIVE', '2023-06-15'),
('ACC-1013', 'CUST-013', 'CURRENT', 340000.00, 'ACTIVE', '2023-07-02'),
('ACC-1014', 'CUST-014', 'SAVINGS', 62000.00, 'ACTIVE', '2023-07-20'),
('ACC-1015', 'CUST-015', 'SALARY', 85000.00, 'ACTIVE', '2023-08-01'),
('ACC-1016', 'CUST-016', 'SAVINGS', 51000.00, 'ACTIVE', '2023-08-14'),
('ACC-1017', 'CUST-017', 'CURRENT', 145000.00, 'ACTIVE', '2023-09-01'),
('ACC-1018', 'CUST-018', 'SAVINGS', 38000.00, 'ACTIVE', '2023-09-18'),
('ACC-1019', 'CUST-019', 'SALARY', 91000.00, 'ACTIVE', '2023-10-01'),
('ACC-1020', 'CUST-020', 'SAVINGS', 47000.00, 'ACTIVE', '2023-10-15');

-- 20 Transactions
INSERT INTO account_transactions (transaction_id, account_number, txn_type, amount, balance_after, description) VALUES
('TXN-8801', 'ACC-1001', 'CREDIT', 15000.00, 45000.00, 'Payroll direct deposit'),
('TXN-8802', 'ACC-1002', 'DEBIT', 25000.00, 125000.00, 'Vendor invoice settlement'),
('TXN-8803', 'ACC-1003', 'CREDIT', 68000.00, 68000.00, 'Monthly salary deposit'),
('TXN-8804', 'ACC-1004', 'DEBIT', 3500.00, 32000.00, 'Electricity and utility bill'),
('TXN-8805', 'ACC-1005', 'CREDIT', 12000.00, 89000.00, 'Client consulting payment'),
('TXN-8806', 'ACC-1006', 'DEBIT', 50000.00, 210000.00, 'Office lease rent payment'),
('TXN-8807', 'ACC-1007', 'CREDIT', 54000.00, 54000.00, 'Monthly salary deposit'),
('TXN-8808', 'ACC-1008', 'CREDIT', 8000.00, 96000.00, 'Mutual fund dividend payout'),
('TXN-8809', 'ACC-1009', 'DEBIT', 18000.00, 175000.00, 'Hardware equipment purchase'),
('TXN-8810', 'ACC-1010', 'DEBIT', 2500.00, 41000.00, 'E-commerce retail purchase'),
('TXN-8811', 'ACC-1011', 'CREDIT', 73000.00, 73000.00, 'Monthly salary deposit'),
('TXN-8812', 'ACC-1012', 'DEBIT', 4200.00, 29000.00, 'Groceries and retail shopping'),
('TXN-8813', 'ACC-1013', 'CREDIT', 95000.00, 340000.00, 'Corporate client project retainer'),
('TXN-8814', 'ACC-1014', 'DEBIT', 6000.00, 62000.00, 'Flight tickets booking'),
('TXN-8815', 'ACC-1015', 'CREDIT', 85000.00, 85000.00, 'Monthly salary deposit'),
('TXN-8816', 'ACC-1016', 'DEBIT', 1500.00, 51000.00, 'Mobile and broadband bill'),
('TXN-8817', 'ACC-1017', 'CREDIT', 45000.00, 145000.00, 'Merchant sales POS settlement'),
('TXN-8818', 'ACC-1018', 'DEBIT', 2200.00, 38000.00, 'Pharmacy and medical expense'),
('TXN-8819', 'ACC-1019', 'CREDIT', 91000.00, 91000.00, 'Monthly salary deposit'),
('TXN-8820', 'ACC-1020', 'DEBIT', 5000.00, 47000.00, 'Insurance premium debit');
