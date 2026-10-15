# Polymorphism

Polymorphism means "many forms." It allows us to perform a single action in different ways.

## 1. Compile-Time Polymorphism (Method Overloading)
Occurs when multiple methods in the *same class* have the same name but different parameters (type, number, or order of parameters).
*   Resolved by the compiler during compilation based on the arguments passed.

```java
// Overloading Example
public void printReceipt(String customerName) { ... }
public void printReceipt(String customerName, double amount) { ... }
```

## 2. Runtime Polymorphism (Method Overriding)
Occurs when a subclass provides a specific implementation for a method that is already defined in its parent class.
*   Resolved by the JVM at runtime based on the *actual object type*, not the reference type. (This is called Dynamic Method Dispatch).
*   Always use the `@Override` annotation. It tells the compiler to verify that you are actually overriding a method correctly.

```java
class Animal {
    public void makeSound() { System.out.println("Generic sound"); }
}

class Dog extends Animal {
    @Override
    public void makeSound() { System.out.println("Bark!"); }
}

// Runtime Polymorphism in action:
Animal myPet = new Dog();
myPet.makeSound(); // Outputs "Bark!" because the actual object is a Dog.
```

## 3. Upcasting and Downcasting
*   **Upcasting:** Assigning a child object to a parent reference (e.g., `Payment p = new CardPayment();`). Implicit and safe.
*   **Downcasting:** Assigning a parent reference back to a child reference (e.g., `CardPayment cp = (CardPayment) p;`). Explicit and potentially unsafe. Always use the `instanceof` operator to check before downcasting.
