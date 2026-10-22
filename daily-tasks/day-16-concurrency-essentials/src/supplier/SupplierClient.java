package supplier;

import model.SupplierQuote;

/**
 * Contract for remote price quote provider.
 */
@FunctionalInterface
public interface SupplierClient {
    SupplierQuote fetchPrice(String productId) throws Exception;
}
