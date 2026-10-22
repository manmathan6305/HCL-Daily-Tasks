# Method References in Java

A method reference (`::`) is a compact syntactic shorthand for a lambda expression that simply forwards its arguments to an existing method.

## The 4 Kinds of Method References

| Kind | Syntax | Lambda Equivalent | Real Example |
| :--- | :--- | :--- | :--- |
| **1. Static Method Reference** | `ContainingClass::staticMethod` | `(args) -> ContainingClass.staticMethod(args)` | `ProductValidator::isAffordable` |
| **2. Instance Method of a Particular Object** | `containingObject::instanceMethod` | `(args) -> containingObject.instanceMethod(args)` | `System.out::println` |
| **3. Instance Method of an Arbitrary Object of a Type** | `ContainingType::instanceMethod` | `(target, args) -> target.instanceMethod(args)` | `ProductItem::getPrice` |
| **4. Constructor Reference** | `ClassName::new` | `(args) -> new ClassName(args)` | `ArrayList::new`, `ProductItem::new` |

### Refactoring Rule of Thumb
If a lambda expression simply calls a method with identical arguments (`item -> item.getName()`), replace it with a method reference (`ProductItem::getName`).
