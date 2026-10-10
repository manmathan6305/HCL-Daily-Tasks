import exceptions.InsufficientStockException;
import exceptions.InvalidQuantityException;
import java.util.Scanner;

public class OrderProcessor {
    private int availableStock = 10;
    
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Order Processor Menu ---");
            System.out.println("1. View Stock");
            System.out.println("2. Place Order");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            
            String choice = scanner.nextLine();
            
            if (choice.equals("1")) {
                System.out.println("Current available stock: " + availableStock);
            } else if (choice.equals("2")) {
                System.out.print("Enter order quantity: ");
                String input = scanner.nextLine();
                processOrderInput(input);
            } else if (choice.equals("3")) {
                System.out.println("Exiting application.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    private void processOrderInput(String input) {
        boolean success = false;
        try {
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalStateException("Input cannot be empty");
            }
            int quantity = Integer.parseInt(input);
            processOrder(quantity);
            System.out.println("Success! Order placed for " + quantity + " units.");
            success = true;
        } catch (NumberFormatException | IllegalStateException e) {
            System.out.println("Error: Please enter a valid numeric quantity.");
            // Example of exception chaining for demonstration
            RuntimeException chained = new RuntimeException("Input parsing failed", e);
            System.out.println("   [Debug] Chained exception: " + chained.getMessage() + ", Cause: " + chained.getCause());
        } catch (InvalidQuantityException e) {
            System.out.println("Validation Error: " + e.getMessage());
        } catch (InsufficientStockException e) {
            System.out.println("Stock Error: " + e.getMessage());
        } finally {
            System.out.println("Audit log: Processed order attempt. Success: " + success);
        }
    }
    
    private void processOrder(int quantity) throws InsufficientStockException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Order quantity must be greater than zero.");
        }
        
        if (quantity > availableStock) {
            throw new InsufficientStockException("Requested quantity (" + quantity + ") exceeds available stock (" + availableStock + ").");
        }
        
        // Process order
        availableStock -= quantity;
    }

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        processor.displayMenu();
    }
}
