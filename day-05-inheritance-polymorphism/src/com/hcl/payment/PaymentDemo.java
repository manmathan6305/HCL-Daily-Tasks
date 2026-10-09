package com.hcl.payment;

// --- Interfaces and Abstract Classes ---

interface Refundable {
    void processRefund(double amount);
}

abstract class Payment {
    protected String transactionId;
    protected double amount;

    public Payment(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.amount = amount;
    }

    // Abstract method: MUST be implemented by child classes
    public abstract void processPayment();

    // Concrete method: inherited by child classes
    public void printReceipt() {
        System.out.println("Receipt for Txn: " + transactionId + " | Amount: $" + amount);
    }
}

// --- Concrete Classes ---

class CardPayment extends Payment implements Refundable {
    private String cardNumber;

    public CardPayment(String transactionId, double amount, String cardNumber) {
        super(transactionId, amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing Card Payment for ending in " + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Status: SUCCESS");
    }

    @Override
    public void processRefund(double amount) {
        System.out.println("Refunding $" + amount + " to Card ending in " + cardNumber.substring(cardNumber.length() - 4));
    }
}

class UPIPayment extends Payment {
    private String upiId;

    public UPIPayment(String transactionId, double amount, String upiId) {
        super(transactionId, amount);
        this.upiId = upiId;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing UPI Payment for ID: " + upiId);
        System.out.println("Status: SUCCESS");
    }
}

class CashPayment extends Payment {
    public CashPayment(String transactionId, double amount) {
        super(transactionId, amount);
    }

    @Override
    public void processPayment() {
        System.out.println("Processing Cash Payment at Counter.");
        System.out.println("Status: SUCCESS (Physical Cash Received)");
    }
}

// --- Main Demonstration Class ---

public class PaymentDemo {

    // Compile-time Polymorphism (Method Overloading)
    public void processReceipt(Payment p) {
        p.printReceipt();
    }

    public void processReceipt(Payment p, boolean detailed) {
        p.printReceipt();
        if (detailed) {
            System.out.println("Detailed Terms and Conditions apply.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Payment Processing System ===");

        // Array of abstract type 'Payment', holding various concrete subclasses
        Payment[] transactions = {
            new CardPayment("TXN001", 150.00, "1234567890124321"),
            new UPIPayment("TXN002", 45.50, "user@bank"),
            new CashPayment("TXN003", 20.00)
        };

        PaymentDemo demo = new PaymentDemo();

        for (Payment payment : transactions) {
            System.out.println("\n-----------------------------");
            // Runtime Polymorphism (Method Overriding)
            // Java decides at runtime which processPayment() to call based on the object type
            payment.processPayment();
            
            // Overloaded method call
            demo.processReceipt(payment);

            // Checking for Interface implementation using instanceof
            if (payment instanceof Refundable) {
                System.out.println("-> Note: This transaction is eligible for refunds.");
                Refundable ref = (Refundable) payment; // Downcasting safely
                ref.processRefund(payment.amount);
            }
        }
        System.out.println("\n-----------------------------");
    }
}
