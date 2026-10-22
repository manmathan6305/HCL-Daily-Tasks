# The Collectors API Deep Dive

The `java.util.stream.Collectors` class provides comprehensive static factory methods for terminal reduction.

## 1. Grouping and Partitioning

### `groupingBy` with Downstream Collectors
```java
// Grouping by Category and summing total revenue
Map<String, Double> revenue = orders.stream()
    .collect(Collectors.groupingBy(
        Order::getCategory,
        Collectors.summingDouble(Order::getTotalAmount)
    ));
```

### `partitioningBy` (Boolean Predicate Split)
```java
// Partitions orders into delivered (true) vs non-delivered (false)
Map<Boolean, List<Order>> partitioned = orders.stream()
    .collect(Collectors.partitioningBy(Order::isDelivered));
```

---

## 2. Statistical Rollups and String Joins

### `summarizingDouble`
Computes count, sum, min, average, and max in a single single-pass traversal:
```java
DoubleSummaryStatistics stats = orders.stream()
    .collect(Collectors.summarizingDouble(Order::getTotalAmount));
```

### `joining`
```java
String productCatalog = orders.stream()
    .map(Order::getProductName)
    .distinct()
    .sorted()
    .collect(Collectors.joining(", "));
```
