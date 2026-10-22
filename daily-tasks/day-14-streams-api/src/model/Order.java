package model;

import java.util.Objects;

/**
 * Domain model representing a sales transaction order.
 */
public class Order {
    public enum Status { DELIVERED, PENDING, CANCELLED }

    private final String orderId;
    private final String customerId;
    private final String category;
    private final String productName;
    private final int quantity;
    private final double unitPrice;
    private final Status status;

    public Order(String orderId, String customerId, String category, String productName, int quantity, double unitPrice, Status status) {
        this.orderId = Objects.requireNonNull(orderId, "orderId required");
        this.customerId = Objects.requireNonNull(customerId, "customerId required");
        this.category = Objects.requireNonNull(category, "category required");
        this.productName = Objects.requireNonNull(productName, "productName required");
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.status = Objects.requireNonNull(status, "status required");
    }

    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public String getCategory() { return category; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public Status getStatus() { return status; }

    public double getTotalAmount() {
        return quantity * unitPrice;
    }

    public boolean isDelivered() {
        return status == Status.DELIVERED;
    }

    @Override
    public String toString() {
        return String.format("Order[%s | Cust: %s | %s: '%s' x%d @ $%.2f = $%.2f | %s]",
                orderId, customerId, category, productName, quantity, unitPrice, getTotalAmount(), status);
    }
}
