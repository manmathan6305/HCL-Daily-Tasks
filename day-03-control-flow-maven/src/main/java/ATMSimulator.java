import java.util.Scanner;

public class ATMSimulator {
    private static final int CORRECT_PIN = 1234;
    private static final int MAX_ATTEMPTS = 3;
    private static double balance = 1000.00; // Starting balance

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Welcome to HCL Bank ATM ===");

        // 1. PIN Authentication using While Loop
        int attempts = 0;
        boolean authenticated = false;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Please enter your 4-digit PIN: ");
            if (scanner.hasNextInt()) {
                int enteredPin = scanner.nextInt();
                if (enteredPin == CORRECT_PIN) {
                    authenticated = true;
                    break;
                } else {
                    attempts++;
                    System.out.println("Incorrect PIN. Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                }
            } else {
                System.out.println("Invalid input. Please enter numbers only.");
                scanner.next(); // Clear invalid input
                attempts++;
            }
        }

        if (!authenticated) {
            System.out.println("Maximum attempts reached. Account locked for security.");
            scanner.close();
            return; // Exit program
        }

        // 2. ATM Menu using Do-While Loop and Switch Statement
        int choice = 0;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Balance Enquiry");
            System.out.println("2. Deposit");
            System.out.println("3. Withdrawal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.printf("Current Balance: $%.2f\n", balance);
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: $");
                        if (scanner.hasNextDouble()) {
                            double deposit = scanner.nextDouble();
                            if (deposit > 0) {
                                balance += deposit;
                                System.out.printf("Successfully deposited $%.2f. New balance: $%.2f\n", deposit, balance);
                            } else {
                                System.out.println("Deposit amount must be greater than zero.");
                            }
                        } else {
                            System.out.println("Invalid amount format.");
                            scanner.next(); // Clear input
                        }
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: $");
                        if (scanner.hasNextDouble()) {
                            double withdrawal = scanner.nextDouble();
                            if (withdrawal > 0 && withdrawal <= balance) {
                                balance -= withdrawal;
                                System.out.printf("Successfully withdrew $%.2f. New balance: $%.2f\n", withdrawal, balance);
                            } else if (withdrawal > balance) {
                                System.out.println("Insufficient funds.");
                            } else {
                                System.out.println("Withdrawal amount must be greater than zero.");
                            }
                        } else {
                            System.out.println("Invalid amount format.");
                            scanner.next(); // Clear input
                        }
                        break;
                    case 4:
                        System.out.println("Thank you for using HCL Bank ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1-4.");
                }
            } else {
                System.out.println("Invalid choice format. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        } while (choice != 4);

        scanner.close();
    }
}
