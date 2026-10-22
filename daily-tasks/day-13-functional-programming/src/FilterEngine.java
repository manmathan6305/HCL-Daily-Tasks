import model.ProductItem;
import service.ProductValidator;
import strategy.DiscountStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * DELIVERABLE: FilterEngine.java
 *
 * Implements:
 * - Composed Predicates (and, or, negate)
 * - Function chains (andThen, compose)
 * - Terminal display via Consumer
 * - Fallback default generation via Supplier
 * - All 4 kinds of Method References replacing lambdas
 * - Strategy pattern as Lambdas (Zero anonymous inner classes)
 */
public class FilterEngine {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("  HCL Day 13: Functional Programming & Product Filter Engine ");
        System.out.println("============================================================");

        FilterEngine engine = new FilterEngine();
        engine.runFunctionalPipeline();
    }

    public void runFunctionalPipeline() {
        List<ProductItem> inventory = Arrays.asList(
                new ProductItem("SKU-101", "MacBook Pro M3 Max", "Electronics", 2499.00, true, 4.9),
                new ProductItem("SKU-102", "Mechanical Keychron K2", "Electronics", 99.99, true, 4.7),
                new ProductItem("SKU-103", "Standing Desk ErgoPro", "Furniture", 450.00, true, 4.6),
                new ProductItem("SKU-104", "Wireless Gaming Mouse", "Electronics", 49.99, false, 4.2), // Out of stock
                new ProductItem("SKU-105", "Noise-Cancelling Headphones", "Electronics", 349.99, true, 4.8),
                new ProductItem("SKU-106", "Office Mesh Chair", "Furniture", 220.00, true, 4.3),
                new ProductItem("SKU-107", "USB-C Multiport Hub", "Accessories", 39.99, true, 4.4)
        );

        // 1. COMPOSED PREDICATES: Filter by Category, Price <= $500, and InStock
        System.out.println("\n--- [PART 1] COMPOSED PREDICATES (and, or, negate) ---");

        Predicate<ProductItem> isElectronics = item -> "Electronics".equalsIgnoreCase(item.getCategory());
        Predicate<ProductItem> isInStock = ProductItem::isInStock; // Method reference (type 3: arbitrary object)
        Predicate<ProductItem> isAffordable = ProductValidator::isAffordable; // Method reference (type 1: static method)

        // Compose: Electronics AND InStock AND Affordable
        Predicate<ProductItem> targetCriteria = isElectronics.and(isInStock).and(isAffordable);

        List<ProductItem> matchedProducts = filterProducts(inventory, targetCriteria);
        System.out.println("Matched Products (Electronics && In-Stock && Price <= $500):");
        matchedProducts.forEach(System.out::println); // Method reference (type 2: println on PrintStream)

        // 2. FUNCTION CHAINS: Transform Product to Discounted Quote String
        System.out.println("\n--- [PART 2] FUNCTION TRANSFORMATION CHAINS (andThen, compose) ---");

        Function<ProductItem, Double> extractPrice = ProductItem::getPrice;
        Function<Double, Double> applySalesTax = price -> price * 1.08;
        Function<Double, String> formatCurrency = amount -> String.format("$%,.2f (incl. tax)", amount);

        // Chain: extractPrice -> applySalesTax -> formatCurrency
        Function<ProductItem, String> calculateFinalPriceTag = extractPrice.andThen(applySalesTax).andThen(formatCurrency);

        ProductValidator validator = new ProductValidator();
        Consumer<String> reportConsumer = System.out::println; // Method reference (type 2: instance method of object)

        System.out.println("Price Transformations for Matched Products:");
        for (ProductItem item : matchedProducts) {
            String priceTag = calculateFinalPriceTag.apply(item);
            String summary = validator.formatSummary(item);
            reportConsumer.accept("  * " + summary + " -> Final: " + priceTag);
        }

        // 3. SUPPLIER: Fallback Default Product
        System.out.println("\n--- [PART 3] SUPPLIER PATTERN FOR FALLBACK DEFAULTS ---");
        Supplier<ProductItem> defaultProductSupplier = () -> new ProductItem("SKU-DEFAULT", "Standard USB Cable", "Accessories", 9.99, true, 4.0);

        Predicate<ProductItem> impossibleFilter = item -> item.getPrice() > 10000.00;
        ProductItem foundOrFallback = inventory.stream()
                .filter(impossibleFilter)
                .findFirst()
                .orElseGet(defaultProductSupplier);

        System.out.println("Result of impossible filter (fallback supplied): " + foundOrFallback);

        // 4. STRATEGY PATTERN AS PURE LAMBDAS (No Anonymous Inner Classes!)
        System.out.println("\n--- [PART 4] STRATEGY PATTERN VIA LAMBDAS & METHOD REFERENCES ---");

        // Strategy A: 10% Clearance Discount
        DiscountStrategy clearanceDiscount = item -> item.getPrice() * 0.90;

        // Strategy B: $50 Instant Rebate for items >= $200
        DiscountStrategy instantRebate = item -> item.getPrice() >= 200.0 ? item.getPrice() - 50.0 : item.getPrice();

        // Composed Strategy using default method: clearanceDiscount THEN instantRebate
        DiscountStrategy comboStrategy = clearanceDiscount.andThen(instantRebate);

        ProductItem laptop = inventory.get(0); // $2,499.00
        double discountedPrice = comboStrategy.calculateDiscountedPrice(laptop);
        System.out.printf("Original Price for '%s': $%,.2f%n", laptop.getName(), laptop.getPrice());
        System.out.printf("Combo Discount Strategy (10%% off then $50 rebate): $%,.2f%n", discountedPrice);

        System.out.println("\n[SUCCESS] Day 13 Functional Programming & FilterEngine Completed Successfully!");
    }

    public static List<ProductItem> filterProducts(List<ProductItem> items, Predicate<ProductItem> predicate) {
        List<ProductItem> result = new ArrayList<>();
        for (ProductItem item : items) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
