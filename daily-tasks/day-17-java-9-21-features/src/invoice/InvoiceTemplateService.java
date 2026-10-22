package invoice;

import java.time.format.DateTimeFormatter;

/**
 * Demonstrates modern Java Text Blocks (triple quotes) for clean multiline templating.
 */
public class InvoiceTemplateService {

    public static String buildEmailNotification(Invoice invoice) {
        var dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        var itemsTable = new StringBuilder();

        for (var item : invoice.items()) {
            itemsTable.append(String.format("   - %-20s (x%d) @ $%-8.2f = $%.2f%n",
                    item.description(), item.quantity(), item.unitPrice(), item.subtotal()));
        }

        // Text block formatting with formatted
        return """
        ========================================================================
        TO: %s <%s>
        DATE: %s
        SUBJECT: Official Tax Invoice - %s
        ========================================================================

        Dear %s,

        Thank you for choosing HCL Cloud & Digital Solutions.
        Your subscription / purchase has been processed successfully.

        INVOICE SUMMARY:
        ------------------------------------------------------------------------
        Invoice Number : %s
        Date of Issue  : %s
        Tax Rate       : %.1f%%

        LINE ITEMS:
        %s
        ------------------------------------------------------------------------
        Subtotal       : $%.2f
        Tax            : $%.2f
        TOTAL DUE      : $%.2f
        ------------------------------------------------------------------------

        PAYMENT TERMS:
        This is an automatically generated receipt. All payments have been
        settled through your pre-authorized corporate billing agreement.
        
        Best regards,
        HCL Enterprise Billing Team
        support-billing@hcl.com
        ========================================================================
        """.formatted(
                invoice.customerName(),
                invoice.customerEmail(),
                invoice.issueDate().format(dateFormatter),
                invoice.invoiceNumber(),
                invoice.customerName(),
                invoice.invoiceNumber(),
                invoice.issueDate().format(dateFormatter),
                invoice.taxRate() * 100,
                itemsTable.toString().stripTrailing(),
                invoice.calculateSubtotal(),
                invoice.calculateTax(),
                invoice.calculateTotal()
        );
    }
}
