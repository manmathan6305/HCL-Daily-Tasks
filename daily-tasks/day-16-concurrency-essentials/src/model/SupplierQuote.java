package model;

import java.time.Instant;

/**
 * Immutable quote representation holding supplier pricing or error fallback details.
 */
public class SupplierQuote {
    private final String supplierName;
    private final String productId;
    private final double price;
    private final long responseTimeMs;
    private final boolean successful;
    private final String errorMessage;
    private final Instant timestamp;

    public SupplierQuote(String supplierName, String productId, double price, long responseTimeMs,
                         boolean successful, String errorMessage) {
        this.supplierName = supplierName;
        this.productId = productId;
        this.price = price;
        this.responseTimeMs = responseTimeMs;
        this.successful = successful;
        this.errorMessage = errorMessage;
        this.timestamp = Instant.now();
    }

    public static SupplierQuote success(String supplierName, String productId, double price, long responseTimeMs) {
        return new SupplierQuote(supplierName, productId, price, responseTimeMs, true, null);
    }

    public static SupplierQuote failure(String supplierName, String productId, String errorMessage, long responseTimeMs) {
        return new SupplierQuote(supplierName, productId, 0.0, responseTimeMs, false, errorMessage);
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public long getResponseTimeMs() {
        return responseTimeMs;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        if (successful) {
            return String.format("[%s] %s: $%.2f (latency: %d ms)", supplierName, productId, price, responseTimeMs);
        } else {
            return String.format("[%s] FAILED (%s - latency: %d ms)", supplierName, errorMessage, responseTimeMs);
        }
    }
}
