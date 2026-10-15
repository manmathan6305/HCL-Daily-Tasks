import model.Product;
import repository.CrudRepository;
import repository.InMemoryRepository;
import resource.AuditLoggerResource;
import resource.DatabaseConnectionResource;
import toolkit.Box;
import toolkit.GenericToolkit;
import toolkit.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Main application demonstrating:
 * - Generic Toolkit: Pair<K,V>, Box<T extends Number>, findMax() (<T extends Comparable<T>>)
 * - PECS (Producer Extends, Consumer Super) wildcards
 * - Try-with-resources CSV reader (products.csv)
 * - Custom AutoCloseable showing reverse LIFO close order
 * - Generic CrudRepository<T, ID> & InMemoryRepository
 */
public class GenericApplication {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("  HCL Day 9: Generics & Try-With-Resources Toolkit           ");
        System.out.println("============================================================");

        GenericApplication app = new GenericApplication();
        app.runAllModules();
    }

    public void runAllModules() {
        // Module 1: Generic Pair & Bounded Box
        demoGenericToolkit();

        // Module 2: PECS Wildcards
        demoPecsWildcards();

        // Module 3: Try-with-resources & Custom AutoCloseable LIFO Close Order
        demoAutoCloseableOrder();

        // Module 4: Seed Data Loading from CSV with Try-With-Resources
        List<Product> products = loadProductsFromCsv();

        // Module 5: Generic CrudRepository & InMemoryRepository
        demoGenericRepository(products);

        System.out.println("\n[SUCCESS] Day 9 Daily Hands-on Task & Project Generic Repository Completed Successfully!");
    }

    private void demoGenericToolkit() {
        System.out.println("\n============================================================");
        System.out.println(" [PART 1] GENERIC TOOLKIT: PAIR<K,V> & BOUNDED BOX<T EXTENDS NUMBER>");
        System.out.println("============================================================");

        // Pair<K, V>
        Pair<String, Integer> studentScore = Pair.of("Alice", 98);
        System.out.println("Original Pair: " + studentScore);
        Pair<Integer, String> swapped = studentScore.swap();
        System.out.println("Swapped Pair:  " + swapped);

        // Box<T extends Number>
        Box<Integer> intBox = new Box<>(450);
        Box<Double> doubleBox = new Box<>(399.95);
        System.out.println("\nNumeric Box 1: " + intBox + " (Double value: " + intBox.doubleValue() + ")");
        System.out.println("Numeric Box 2: " + doubleBox + " (Double value: " + doubleBox.doubleValue() + ")");
        System.out.println("Is intBox > doubleBox? " + intBox.isGreaterThan(doubleBox));

        // Generic bounded findMax
        List<Integer> numbers = List.of(12, 85, 34, 99, 45, 62);
        List<String> names = List.of("Zara", "Bob", "Charlie", "Alice");
        System.out.println("\nfindMax(numbers): " + GenericToolkit.findMax(numbers).orElse(0));
        System.out.println("findMax(names):   " + GenericToolkit.findMax(names).orElse("None"));
    }

    private void demoPecsWildcards() {
        System.out.println("\n============================================================");
        System.out.println(" [PART 2] PECS (PRODUCER EXTENDS, CONSUMER SUPER) WILDCARDS");
        System.out.println("============================================================");

        // Producer Extends: reads from list of doubles and integers
        List<Double> doubleList = List.of(10.5, 20.25, 30.75);
        List<Integer> integerList = List.of(100, 200, 300);
        System.out.printf("Sum of doubles: %.2f%n", GenericToolkit.sumOfNumbers(doubleList));
        System.out.printf("Sum of integers: %.2f%n", GenericToolkit.sumOfNumbers(integerList));

        // Consumer Super: writes integers into a List<Number> or List<Object>
        List<Number> numberConsumer = new ArrayList<>();
        GenericToolkit.addSequentialNumbers(numberConsumer, 4);
        System.out.println("Consumer list after adding: " + numberConsumer);

        // Transfer elements: Producer Extends -> Consumer Super
        List<Integer> source = List.of(1, 2, 3);
        List<Number> destination = new ArrayList<>(List.of(999));
        GenericToolkit.transfer(source, destination);
        System.out.println("Destination after transfer: " + destination);
    }

    private void demoAutoCloseableOrder() {
        System.out.println("\n============================================================");
        System.out.println(" [PART 3] CUSTOM AUTOCLOSEABLE & GUARANTEED LIFO CLOSE ORDER");
        System.out.println("============================================================");
        System.out.println("Opening DatabaseConnectionResource (1st) and AuditLoggerResource (2nd)...");

        // Try-with-resources statement with multiple AutoCloseable resources
        try (DatabaseConnectionResource db = new DatabaseConnectionResource("DB-PROD-CLUSTER");
             AuditLoggerResource logger = new AuditLoggerResource("admin_operator")) {

            db.executeQuery("SELECT * FROM inventory_audit");
            logger.logAction("INVENTORY_CHECK_BATCH");
            System.out.println("  -> Exiting try-block (JVM will now invoke close() in reverse order)...");
        }
    }

    private List<Product> loadProductsFromCsv() {
        System.out.println("\n============================================================");
        System.out.println(" [PART 4] INGEST PRODUCTS.CSV WITH TRY-WITH-RESOURCES");
        System.out.println("============================================================");

        Path current = Paths.get("").toAbsolutePath();
        Path csvPath = current.endsWith("day-09-generics")
                ? Paths.get("data", "products.csv")
                : Paths.get("daily-tasks", "day-09-generics", "data", "products.csv");

        List<Product> products = new ArrayList<>();
        System.out.println("Reading seed data from: " + csvPath);

        // Try-with-resources on BufferedReader
        try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Product p = new Product(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            Double.parseDouble(parts[3].trim()),
                            Integer.parseInt(parts[4].trim())
                    );
                    products.add(p);
                }
            }
            System.out.printf("Successfully loaded %d products from CSV.%n", products.size());
        } catch (IOException e) {
            System.err.println("Failed to read CSV: " + e.getMessage());
        }
        return products;
    }

    private void demoGenericRepository(List<Product> products) {
        System.out.println("\n============================================================");
        System.out.println(" [PART 5] GENERIC CRUDREPOSITORY & INMEMORYREPOSITORY REUSE");
        System.out.println("============================================================");

        // Instantiate generic CrudRepository for Product entity with String ID extractor
        CrudRepository<Product, String> productRepo = new InMemoryRepository<>(Product::getId);

        // 1. Seed Repository
        for (Product p : products) {
            productRepo.save(p);
        }
        System.out.printf("Seeded CrudRepository<Product, String>. Total entity count: %d%n", productRepo.count());

        // 2. Query by ID
        String testId = "PRD-004";
        Optional<Product> found = productRepo.findById(testId);
        System.out.printf("\nQuery product by ID='%s':%n", testId);
        found.ifPresentOrElse(
                p -> System.out.println("   Found: " + p),
                () -> System.out.println("   Product not found.")
        );

        // 3. Find Max by Natural Order (Price)
        Optional<Product> highestPriceProduct = GenericToolkit.findMax(productRepo.findAll());
        highestPriceProduct.ifPresent(p -> System.out.printf("\nHighest priced product (via generic findMax): %s%n", p));

        // 4. Delete and Verify
        System.out.println("\nDeleting product PRD-002...");
        boolean deleted = productRepo.deleteById("PRD-002");
        System.out.printf("Delete status: %s | Remaining entities: %d%n", deleted, productRepo.count());
    }
}
