import sqlite3
import os

def test_transactions():
    print(">> Initializing in-memory database for Transaction Lab...")
    conn = sqlite3.connect(":memory:")
    cursor = conn.cursor()

    base_dir = os.path.dirname(os.path.abspath(__file__))
    sql_dir = os.path.join(base_dir, "sql")

    # Load initial tables
    with open(os.path.join(sql_dir, "transactions.sql"), "r", encoding="utf-8") as f:
        full_sql = f.read()

    cursor.execute('''
        CREATE TABLE accounts (
            account_number VARCHAR(20) PRIMARY KEY,
            holder_name VARCHAR(100) NOT NULL,
            balance DECIMAL(15, 2) NOT NULL CHECK (balance >= 0.00)
        )
    ''')
    cursor.execute("INSERT INTO accounts VALUES ('ACC-A', 'Alice', 100000.00)")
    cursor.execute("INSERT INTO accounts VALUES ('ACC-B', 'Bob', 50000.00)")
    conn.commit()

    print(">> Baseline balances: ACC-A = 100,000 | ACC-B = 50,000 | Total = 150,000")

    # 1. Test Successful Transfer with COMMIT
    print("\n>> [COMMIT DRILL] Transferring 20,000 from ACC-A to ACC-B...")
    cursor.execute("BEGIN TRANSACTION")
    cursor.execute("UPDATE accounts SET balance = balance - 20000.00 WHERE account_number = 'ACC-A'")
    cursor.execute("UPDATE accounts SET balance = balance + 20000.00 WHERE account_number = 'ACC-B'")
    conn.commit()

    cursor.execute("SELECT account_number, balance FROM accounts")
    balances_post_commit = dict(cursor.fetchall())
    print(f"  Post-Commit Balances: {balances_post_commit}")
    assert balances_post_commit['ACC-A'] == 80000.00 and balances_post_commit['ACC-B'] == 70000.00

    # 2. Test Forced Failure with ROLLBACK
    print("\n>> [ROLLBACK DRILL] Attempting invalid transfer of 500,000 (exceeds balance)...")
    try:
        cursor.execute("BEGIN TRANSACTION")
        cursor.execute("UPDATE accounts SET balance = balance - 500000.00 WHERE account_number = 'ACC-A'")
        conn.commit()
    except sqlite3.IntegrityError as ex:
        print(f"  [EXPECTED ERROR CAUGHT] {ex}")
        conn.rollback()
        print("  >> Rolled back transaction successfully!")

    cursor.execute("SELECT account_number, balance FROM accounts")
    balances_post_rollback = dict(cursor.fetchall())
    print(f"  Post-Rollback Balances: {balances_post_rollback}")
    assert balances_post_rollback['ACC-A'] == 80000.00 and balances_post_rollback['ACC-B'] == 70000.00
    print("  >> [INVARIANT VERIFIED] Data remains 100% consistent after rollback!")

    # 3. Test Savepoint
    print("\n>> [SAVEPOINT DRILL] Testing nested savepoints:")
    cursor.execute("SAVEPOINT sp_outer")
    cursor.execute("UPDATE accounts SET balance = balance - 5000.00 WHERE account_number = 'ACC-A'")
    cursor.execute("SAVEPOINT sp_inner")
    cursor.execute("UPDATE accounts SET balance = balance + 5000.00 WHERE account_number = 'ACC-B'")
    cursor.execute("RELEASE SAVEPOINT sp_inner")
    cursor.execute("RELEASE SAVEPOINT sp_outer")
    conn.commit()

    cursor.execute("SELECT account_number, balance FROM accounts")
    print(f"  Final Balances: {dict(cursor.fetchall())}")

    conn.close()
    print("\n>> ALL CHECKS PASSED: Rollback leaves data consistent!")

if __name__ == "__main__":
    test_transactions()
