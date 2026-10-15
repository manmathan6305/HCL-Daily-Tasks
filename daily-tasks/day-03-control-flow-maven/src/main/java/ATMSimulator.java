import java.util.Scanner;

public class ATMSimulator {
    private static final int PIN = 1234;
    private static final int MAX_TRIES = 3;
    private static double balance = 1000.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM");

        int attempts = 0;
        boolean authenticated = false;

        while (attempts < MAX_TRIES) {
            System.out.print("Enter your PIN: ");
            if (scanner.hasNextInt()) {
                if (scanner.nextInt() == PIN) {
                    authenticated = true;
                    break;
                } else {
                    attempts++;
                    System.out.println("Incorrect PIN.");
                }
            } else {
                System.out.println("Invalid input.");
                scanner.next();
                attempts++;
            }
        }

        if (!authenticated) {
            System.out.println("Account locked.");
            scanner.close();
            return;
        }

        int choice = 0;
        do {
            System.out.println("\n1. Balance\n2. Deposit\n3. Withdraw\n4. Exit");
            System.out.print("Choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.printf("Balance: $%.2f\n", balance);
                        break;
                    case 2:
                        System.out.print("Amount to deposit: ");
                        if (scanner.hasNextDouble()) {
                            double amount = scanner.nextDouble();
                            if (amount > 0) {
                                balance += amount;
                                System.out.printf("New balance: $%.2f\n", balance);
                            } else {
                                System.out.println("Invalid amount.");
                            }
                        } else {
                            System.out.println("Invalid input.");
                            scanner.next();
                        }
                        break;
                    case 3:
                        System.out.print("Amount to withdraw: ");
                        if (scanner.hasNextDouble()) {
                            double amount = scanner.nextDouble();
                            if (amount > 0 && amount <= balance) {
                                balance -= amount;
                                System.out.printf("New balance: $%.2f\n", balance);
                            } else {
                                System.out.println("Invalid amount or insufficient funds.");
                            }
                        } else {
                            System.out.println("Invalid input.");
                            scanner.next();
                        }
                        break;
                    case 4:
                        System.out.println("Goodbye.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid input.");
                scanner.next();
            }
        } while (choice != 4);

        scanner.close();
    }
}
