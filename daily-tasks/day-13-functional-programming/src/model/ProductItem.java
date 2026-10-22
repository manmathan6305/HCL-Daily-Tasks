package model;

import java.util.Objects;

/**
 * Domain entity representing a product for the functional filtering engine.
 */
public class ProductItem {
    private final String sku;
    private final String name;
    private final String category;
    private final double price;
    private final boolean inStock;
    private final double rating;

    public ProductItem(String sku, String name, String category, double price, boolean inStock, double rating) {
        this.sku = Objects.requireNonNull(sku, "SKU required");
        this.name = Objects.requireNonNull(name, "Name required");
        this.category = Objects.requireNonNull(category, "Category required");
        this.price = price;
        this.inStock = inStock;
        this.rating = rating;
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isInStock() { return inStock; }
    public double getRating() { return rating; }

    @Override
    public String toString() {
        return String.format("[%s] %-24s | Cat: %-12s | Price: $%,8.2f | InStock: %-5s | Rating: %.1f★",
                sku, name, category, price, inStock, rating);
    }
}
