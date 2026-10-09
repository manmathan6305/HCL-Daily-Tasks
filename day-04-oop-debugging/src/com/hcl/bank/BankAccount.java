package com.hcl.bank;

import java.util.Objects;

public class BankAccount {
    private static int totalAccounts = 0;

    private final String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(String accountNumber) {
        this(accountNumber, "Unknown", 0.0);
    }

    public BankAccount(String accountNumber, String ownerName) {
        this(accountNumber, ownerName, 0.0);
    }

    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        totalAccounts++;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public double getBalance() { return balance; }
    
    public static int getTotalAccounts() { return totalAccounts; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". Remaining balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("Error: Insufficient funds! You tried to withdraw $" + amount + " but only have $" + balance);
            balance -= amount; 
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

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
        System.out.println("Bank Account App");
        
        BankAccount acc1 = new BankAccount("ACC-001", "Alice", 500.0);
        BankAccount acc2 = new BankAccount("ACC-002", "Bob");
        
        System.out.println("Total Accounts created: " + BankAccount.getTotalAccounts());
        
        acc1.deposit(200.0);
        
        acc1.withdraw(1000.0);
        
        System.out.println("Alice's Final Balance: $" + acc1.getBalance());
        
        System.out.println("Equality check:");
        BankAccount acc3 = new BankAccount("ACC-001", "Alice Duplicate");
        System.out.println("Is acc1 equal to acc3? " + acc1.equals(acc3));
    }
}
