package com.hcl.bank;

import java.util.Objects;

public class BankAccount {
    // 1. Static field (Class level)
    private static int totalAccounts = 0;

    // 2. Private fields (Encapsulation)
    private final String accountNumber;
    private String ownerName;
    private double balance;

    // 3. Constructors & Constructor Chaining
    public BankAccount(String accountNumber) {
        this(accountNumber, "Unknown", 0.0); // Calls the master constructor
    }

    public BankAccount(String accountNumber, String ownerName) {
        this(accountNumber, ownerName, 0.0);
    }

    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        totalAccounts++; // Increment shared counter
    }

    // 4. Getters and Setters
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public double getBalance() { return balance; }
    
    public static int getTotalAccounts() { return totalAccounts; }

    // 5. Business Methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        // INTENTIONAL BUG FOR DEBUGGING PRACTICE:
        // The condition below has a logical flaw. 
        // Can you find it by stepping through with a debugger?
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". Remaining balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("Error: Insufficient funds! You tried to withdraw $" + amount + " but only have $" + balance);
            // Bug: It accidentally deducts anyway!
            balance -= amount; 
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    // 6. Overriding equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return accountNumber.equals(that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    public static void main(String[] args) {
        System.out.println("=== Bank Account OOP & Debugging ===");
        
        BankAccount acc1 = new BankAccount("ACC-001", "Alice", 500.0);
        BankAccount acc2 = new BankAccount("ACC-002", "Bob");
        
        System.out.println("Total Accounts created: " + BankAccount.getTotalAccounts());
        
        acc1.deposit(200.0);
        
        // This withdrawal will trigger the intentional bug! 
        // Put a breakpoint on the next line and debug it.
        acc1.withdraw(1000.0);
        
        System.out.println("Alice's Final Balance: $" + acc1.getBalance());
        
        System.out.println("\nEquality check:");
        BankAccount acc3 = new BankAccount("ACC-001", "Alice Duplicate");
        System.out.println("Is acc1 equal to acc3? " + acc1.equals(acc3));
    }
}
