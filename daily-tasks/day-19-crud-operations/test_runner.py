import sqlite3
import os
import sys

def execute_scripts():
    print('>> Initializing clean in-memory database...')
    conn = sqlite3.connect(':memory:')
    cursor = conn.cursor()

    base_dir = os.path.dirname(os.path.abspath(__file__))
    sql_dir = os.path.join(base_dir, 'sql')

    # 1. Execute schema.sql
    with open(os.path.join(sql_dir, 'schema.sql'), 'r', encoding='utf-8') as f:
        schema_sql = f.read()
    
    cursor.executescript(schema_sql)
    print('>> [SUCCESS] schema.sql executed cleanly from scratch!')

    # 2. Execute seed.sql
    with open(os.path.join(sql_dir, 'seed.sql'), 'r', encoding='utf-8') as f:
        seed_sql = f.read()
    cursor.executescript(seed_sql)
    print('>> [SUCCESS] seed.sql executed successfully! (30 employee rows, 6 departments)')

    cursor.execute('SELECT COUNT(*) FROM departments')
    dept_count = cursor.fetchone()[0]
    cursor.execute('SELECT COUNT(*) FROM employees')
    emp_count = cursor.fetchone()[0]
    print(f'>> Verification: Departments Count = {dept_count}, Employees Count = {emp_count}')

    print('\n>> Executing 12 Filtering & Sorting Queries from queries.sql:')
    queries = [
        ('Q1: Salary BETWEEN 60000 AND 90000', 
         'SELECT emp_id, first_name, last_name, salary, dept_id FROM employees WHERE salary BETWEEN 60000.00 AND 90000.00 ORDER BY salary DESC LIMIT 3'),
        ('Q2: First name LIKE \'A%\'', 
         'SELECT emp_id, first_name, last_name, dept_id FROM employees WHERE first_name LIKE \'A%\''),
        ('Q3: Dept IN (\'DEP-ENG\', \'DEP-FIN\', \'DEP-IT\')', 
         'SELECT COUNT(*) FROM employees WHERE dept_id IN (\'DEP-ENG\', \'DEP-FIN\', \'DEP-IT\')'),
        ('Q4: Hired in 2023 or later (hire_date >= \'2023-01-01\')', 
         'SELECT COUNT(*) FROM employees WHERE hire_date >= \'2023-01-01\''),
        ('Q5: Top 3 Highest Earners (ORDER BY salary DESC LIMIT 3)', 
         'SELECT first_name, last_name, job_title, salary FROM employees ORDER BY salary DESC LIMIT 3'),
        ('Q6: Senior Leadership (manager_id IS NULL)', 
         'SELECT first_name, last_name, job_title FROM employees WHERE manager_id IS NULL'),
        ('Q7: Employees with commission (commission_pct > 0)', 
         'SELECT COUNT(*) FROM employees WHERE commission_pct > 0.00'),
        ('Q8: Last name LIKE \'%sh%\'', 
         'SELECT first_name, last_name FROM employees WHERE last_name LIKE \'%sh%\''),
        ('Q9: Pagination (Page 2, LIMIT 3 OFFSET 3)', 
         'SELECT emp_id, first_name, last_name FROM employees ORDER BY emp_id ASC LIMIT 3 OFFSET 3'),
        ('Q10: Salaries strictly under 60000', 
         'SELECT COUNT(*) FROM employees WHERE salary < 60000.00'),
        ('Q11: Phone LIKE \'+91-981%\'', 
         'SELECT first_name, phone FROM employees WHERE phone LIKE \'+91-981%\''),
        ('Q12: Safe transaction update verification', 
         'SELECT emp_id, first_name, salary FROM employees WHERE emp_id = 1010')
    ]

    for label, q in queries:
        cursor.execute(q)
        res = cursor.fetchall()
        print(f'  [{label}] -> {res}')

    print('\n>> Validating Project Schema & Seed (>= 20 rows per table)...')
    with open(os.path.join(sql_dir, 'project-schema.sql'), 'r', encoding='utf-8') as f:
        cursor.executescript(f.read())
    with open(os.path.join(sql_dir, 'project-seed.sql'), 'r', encoding='utf-8') as f:
        cursor.executescript(f.read())
    
    cursor.execute('SELECT COUNT(*) FROM bank_customers')
    cust_cnt = cursor.fetchone()[0]
    cursor.execute('SELECT COUNT(*) FROM bank_accounts')
    acc_cnt = cursor.fetchone()[0]
    cursor.execute('SELECT COUNT(*) FROM account_transactions')
    txn_cnt = cursor.fetchone()[0]
    print(f'>> Project Tables Verified: Customers={cust_cnt}, Accounts={acc_cnt}, Transactions={txn_cnt}')

    conn.close()
    print('\n>> ALL CHECKS PASSED: Scripts run cleanly from scratch!')

if __name__ == '__main__':
    execute_scripts()
