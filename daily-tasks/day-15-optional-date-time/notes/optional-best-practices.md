# Java Optional Best Practices Guide

## 1. What Problem Does `Optional<T>` Solve?
`Optional<T>` was introduced in Java 8 to represent the **absence of a value** as an explicit return type. Its primary purpose is to eliminate silent `NullPointerException` (NPE) bugs by forcing API callers to consciously address potential nullability.

---

## 2. When to Use `Optional`
- **Method Return Types:** Whenever a method might reasonably not find a result (e.g., `findById()`, `findFirst()`, `lookup()`).
- **Stream Reductions:** Operations like `stream.min()`, `stream.max()`, `stream.reduce()`.
- **Public API Contracts:** Expresses optionality clearly to library consumers.

---

## 3. When NOT to Use `Optional`
| Anti-Pattern | Why It Is Harmful | Preferred Alternative |
| :--- | :--- | :--- |
| **Class Fields / Entity Attributes** | `Optional` does not implement `Serializable`. Adds extra object wrapper overhead on heap (16-24 bytes per field). | Keep field nullable internally, provide a getter returning `Optional<T>`. |
| **Method Parameters** | Forces caller to wrap arguments in `Optional.ofNullable()`, cluttering callsites and allowing `null` to still be passed as the Optional reference. | Use overloaded methods or `@Nullable` annotations. |
| **Inside Collections / Maps** | Putting `Optional` inside a `List` or `Map` keys/values doubles pointer indirection and memory overhead. | Use empty collection `List.of()` or check `containsKey()`. |
| **Primitive Types** | `Optional<Integer>`, `Optional<Double>` incur costly autoboxing/unboxing. | Use `OptionalInt`, `OptionalLong`, `OptionalDouble`. |

---

## 4. Fluent Chaining Cheat Sheet
```java
// 1. Chained extraction
String email = findUser(id)
    .flatMap(User::getProfile)
    .map(Profile::getEmail)
    .orElse("support@system.com");

// 2. Safe validation
Optional<Subscription> activeSub = findSubscription(subId)
    .filter(sub -> !sub.isExpired())
    .filter(Subscription::isAutoRenew);

// 3. Throwing domain exception
Subscription sub = findSubscription(subId)
    .orElseThrow(() -> new SubscriptionNotFoundException("Not found: " + subId));

// 4. Action on presence or absence
userOpt.ifPresentOrElse(
    u -> sendWelcomeEmail(u),
    () -> log.warn("User lookup returned empty")
);
```
