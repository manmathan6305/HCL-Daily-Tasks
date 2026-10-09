# Inheritance and Abstract Classes

## 1. Inheritance (`extends`)
Inheritance allows a new class (subclass/child) to acquire the properties and methods of an existing class (superclass/parent). This promotes code reusability.
*   Java only supports single inheritance for classes (a class can only extend ONE parent class).
*   Use the `extends` keyword.
*   Constructors are *not* inherited, but the child constructor must call the parent constructor (implicitly or explicitly via `super()`).

## 2. Abstract Classes
An abstract class is a restricted class that cannot be used to create objects directly.
*   Declared using the `abstract` keyword.
*   Can contain both abstract methods (methods without a body that *must* be implemented by subclasses) and regular methods (with a body).
*   Use an abstract class when subclasses share a lot of common code or state (fields).

## 3. Interfaces (`implements`)
An interface is a completely abstract type used to group related methods with empty bodies.
*   A class can implement MULTIPLE interfaces, overcoming the single inheritance limit.
*   All methods in an interface are implicitly `public` and `abstract` (prior to Java 8).
*   Variables in interfaces are implicitly `public`, `static`, and `final` (constants).
*   Use interfaces to define a contract or capability (e.g., `Refundable`, `Printable`) that completely unrelated classes can fulfill.

## 4. Abstract Class vs Interface
| Feature | Abstract Class | Interface |
| :--- | :--- | :--- |
| **Multiple Inheritance** | No (can only extend one) | Yes (can implement many) |
| **State (Fields)** | Can have instance variables | Only `public static final` constants |
| **Constructors** | Yes (called by subclasses) | No |
| **Use Case** | "Is-a" relationship, shared code | "Can-do" behavior, contract |
