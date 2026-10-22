package service;

import model.SupplierQuote;
import supplier.SlowSupplier;

import java.util.ArrayList;
import java.util.List;

/**
 * Baseline: Fetches prices sequentially on the calling thread.
 * Total execution time is the cumulative sum of all supplier latencies.
 */
public class SequentialAggregator {

    public List<SupplierQuote> fetchAll(List<SlowSupplier> suppliers, String productId) {
        List<SupplierQuote> results = new ArrayList<>();
        for (SlowSupplier supplier : suppliers) {
            long start = System.currentTimeMillis();
            try {
                results.add(supplier.fetchPrice(productId));
            } catch (Exception e) {
                long elapsed = System.currentTimeMillis() - start;
                results.add(SupplierQuote.failure(supplier.getSupplierName(), productId, e.getMessage(), elapsed));
            }
        }
        return results;
    }
}
