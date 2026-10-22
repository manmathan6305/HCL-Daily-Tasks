# Prompt Engineering for Core Java Developers

## 1. Why Prompt Specificity Matters
Generative AI models are probabilistic next-token predictors. Vague prompts lead to generic, academic code snippets that lack production constraints. Specific prompts guide the model to select modern, secure, and robust design patterns.

### The C-O-R-E Prompting Framework:
- **Context:** Specify target environment (e.g., Java 21 LTS, Spring Boot 3, Console Plain Java).
- **Objective:** State precisely what class/service is required.
- **Rules & Constraints:** State requirements on immutability, exception types, collections, and complexity.
- **Expected Output:** Specify whether unit tests, interface-implementation pairs, or edge cases should be included.

---

## 2. Examples of Prompt Refinement

| Requirement | Vague Prompt (Avoid) | Production Prompt (Use) |
| :--- | :--- | :--- |
| **Entity Design** | "Write an Order class." | "Write a Java 21 Order domain model. Use private final fields for id, customerId, and timestamp. Store items in an unmodifiable List<OrderItem>. Implement equals/hashCode on id." |
| **Service Layer** | "Create an OrderService to find orders." | "Create an OrderService interface and OrderServiceImpl using Map<String, Order> for O(1) lookups. Throw NoSuchElementException for missing orders. Validate all arguments with Objects.requireNonNull." |
