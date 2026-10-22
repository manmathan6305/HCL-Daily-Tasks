package invoice;

public record InvoiceItem(String sku, String description, int quantity, double unitPrice) {
    public InvoiceItem {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive: " + quantity);
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative: " + unitPrice);
        }
    }

    public double subtotal() {
        return quantity * unitPrice;
    }
}
