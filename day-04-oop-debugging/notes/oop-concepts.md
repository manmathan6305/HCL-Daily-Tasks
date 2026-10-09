# OOP Concepts

## 1. Classes and Objects
*   **Class:** A blueprint or template. It defines the state (fields/variables) and behavior (methods) of a specific type.
*   **Object:** An instance of a class. Created using the `new` keyword.

## 2. Constructors
Special methods used to initialize objects. They have the exact same name as the class and no return type.
*   **Overloading:** Having multiple constructors with different parameter lists.
*   **Chaining:** Calling one constructor from another using `this()` to avoid code duplication.

## 3. Encapsulation & Access Modifiers
Encapsulation is the principle of hiding internal state and requiring all interaction to be performed through an object's methods.
*   `private`: Accessible only within the same class. (Use for most fields).
*   `default` (no keyword): Accessible within the same package.
*   `protected`: Accessible within the same package and by subclasses.
*   `public`: Accessible from anywhere.

## 4. Static vs Instance
*   **Instance members:** Belong to the specific object. E.g., each `BankAccount` object has its own `balance`.
*   **Static members:** Belong to the class itself. E.g., `static int totalAccounts` is shared by all `BankAccount` objects.

## 5. equals() and hashCode()
All classes in Java inherit from `java.lang.Object`.
*   `==` compares memory references (do they point to the exact same object?).
*   `.equals()` should be overridden to compare logical equality (e.g., do these two accounts have the same account number?).
*   If you override `equals()`, you *must* override `hashCode()` to maintain the contract for hashing data structures like `HashMap`.
