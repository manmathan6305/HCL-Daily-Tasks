package supplier;

import model.SupplierQuote;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Simulates a real-world remote e-commerce / inventory supplier with deliberate network latency (1-2s).
 * Optionally simulates intermittent network drops / 503 service failures.
 */
public class SlowSupplier implements SupplierClient {
    private final String supplierName;
    private final long delayMs;
    private final double basePrice;
    private final boolean willFail;

    public SlowSupplier(String supplierName, long delayMs, double basePrice, boolean willFail) {
        this.supplierName = supplierName;
        this.delayMs = delayMs;
        this.basePrice = basePrice;
        this.willFail = willFail;
    }

    public String getSupplierName() {
        return supplierName;
    }

    @Override
    public SupplierQuote fetchPrice(String productId) throws Exception {
        long start = System.currentTimeMillis();
        // Simulate network I/O sleep (1-2 seconds)
        Thread.sleep(delayMs);
        long elapsed = System.currentTimeMillis() - start;

        if (willFail) {
            throw new RuntimeException("HTTP 503 Service Unavailable: " + supplierName + " gateway timeout");
        }

        // Slight price jitter
        double jitter = ThreadLocalRandom.current().nextDouble(-3.0, 3.0);
        double finalPrice = Math.round((basePrice + jitter) * 100.0) / 100.0;
        return SupplierQuote.success(supplierName, productId, finalPrice, elapsed);
    }
}
