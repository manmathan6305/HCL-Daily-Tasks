package invoice;

import java.time.LocalDate;
import java.util.List;

public record Invoice(
        String invoiceNumber,
        String customerName,
        String customerEmail,
        LocalDate issueDate,
        List<InvoiceItem> items,
        double taxRate
) {
    public double calculateSubtotal() {
        return items.stream().mapToDouble(InvoiceItem::subtotal).sum();
    }

    public double calculateTax() {
        return calculateSubtotal() * taxRate;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax();
    }
}
