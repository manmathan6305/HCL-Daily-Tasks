package generator;

import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SalesOrderGenerator {
    private static final String[] CATEGORIES = {"Electronics", "Office Furniture", "Accessories", "Software", "Cloud Services"};
    private static final String[][] PRODUCTS = {
            {"MacBook Pro 16", "Dell XPS 15", "4K UltraFine Monitor", "iPad Pro 12.9"},
            {"Ergonomic Mesh Chair", "Standing Desk Electric", "Bookshelf Storage", "Filing Cabinet"},
            {"Mechanical Keyboard", "Wireless Mouse", "USB-C Dual Dock", "Noise-Cancelling Headset"},
            {"IntelliJ IDEA License", "Enterprise Antivirus", "Operating System Pro", "Database Studio"},
            {"Cloud Compute Node", "Object Storage 1TB", "Managed Kubernetes Cluster", "Virtual Private Network"}
    };
    private static final double[][] BASE_PRICES = {
            {2499.00, 1899.00, 699.00, 1099.00},
            {350.00, 599.00, 180.00, 140.00},
            {120.00, 49.00, 189.00, 249.00},
            {499.00, 89.00, 199.00, 299.00},
            {150.00, 45.00, 450.00, 75.00}
    };

    public static List<Order> generateOrders(int count) {
        List<Order> orders = new ArrayList<>(count);
        Random rng = new Random(42); // Deterministic seed for reproducible testing

        for (int i = 1; i <= count; i++) {
            String orderId = String.format("ORD-%05d", i);
            String customerId = String.format("CUST-%03d", (i % 60) + 1); // 60 unique customers

            int catIdx = rng.nextInt(CATEGORIES.length);
            String category = CATEGORIES[catIdx];

            int prodIdx = rng.nextInt(PRODUCTS[catIdx].length);
            String productName = PRODUCTS[catIdx][prodIdx];
            double unitPrice = BASE_PRICES[catIdx][prodIdx];

            int quantity = rng.nextInt(4) + 1; // 1 to 4 items
            Order.Status status = Order.Status.values()[rng.nextInt(Order.Status.values().length)];

            orders.add(new Order(orderId, customerId, category, productName, quantity, unitPrice, status));
        }
        return orders;
    }
}
