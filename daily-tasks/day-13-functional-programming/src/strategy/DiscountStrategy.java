package strategy;

import model.ProductItem;

/**
 * Custom Functional Interface defining a dynamic pricing strategy.
 */
@FunctionalInterface
public interface DiscountStrategy {
    double calculateDiscountedPrice(ProductItem item);

    // Default combinator method
    default DiscountStrategy andThen(DiscountStrategy after) {
        return item -> {
            double firstPass = this.calculateDiscountedPrice(item);
            ProductItem discountedItem = new ProductItem(item.getSku(), item.getName(), item.getCategory(), firstPass, item.isInStock(), item.getRating());
            return after.calculateDiscountedPrice(discountedItem);
        };
    }
}
