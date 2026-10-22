package service;

import model.ProductItem;

public class ProductValidator {

    // 1. Static method for static method reference: ProductValidator::isAffordable
    public static boolean isAffordable(ProductItem item) {
        return item.getPrice() <= 500.00;
    }

    // 2. Instance method of a particular object: validatorInstance::hasTopRating
    public boolean hasTopRating(ProductItem item) {
        return item.getRating() >= 4.5;
    }

    public String formatSummary(ProductItem item) {
        return String.format("%s (PRICE: $%.2f, RATING: %.1f)", item.getName().toUpperCase(), item.getPrice(), item.getRating());
    }
}
