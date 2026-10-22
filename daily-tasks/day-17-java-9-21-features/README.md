# 📅 Day 17: Java 9-21 Features + Modern APIs

## 📋 Task Overview
- **Topic:** `Java 9-21 Features + Immutable Collections & Modern APIs`
- **Module / Sprint:** Week 3, Sprint 2.2
- **Daily Hands-on Task:** Shape & Invoice Lab: sealed interface `Shape` permits `Circle`, `Rectangle`, `Triangle` (records) with an exhaustive pattern-matching switch for area; an Invoice email template as a text block; show `UnsupportedOperationException` on `List.of`; clean user input with `strip`/`isBlank`.
- **Deliverable:** `ModernJavaLab.java; refactored project`
- **Git Commit:** `day-17: java-9-21-features`
- **Done When Criteria:** `Records + sealed types + switch expressions used meaningfully`

---

## 🏗️ Architecture & Project Structure
```text
day-17-java-9-21-features/
├── deliverables/
│   └── modern-features-breakdown.md    # In-depth architectural analysis
├── notes/
│   ├── modern-java-syntax-updates.md   # var, text blocks, String modern methods
│   └── records-and-sealed-classes.md   # Records & Sealed hierarchies
├── src/
│   ├── collection/
│   │   └── ImmutableCollectionLab.java # List.of, Set.of, UnsupportedOperationException
│   ├── input/
│   │   └── UserInputCleaner.java       # strip, isBlank, lines, repeat
│   ├── invoice/
│   │   ├── Invoice.java                # Immutable Record
│   │   ├── InvoiceItem.java            # Immutable Record
│   │   └── InvoiceTemplateService.java # Java Text Blocks multiline templating
│   ├── shape/
│   │   ├── Circle.java                 # Record with compact constructor
│   │   ├── Rectangle.java              # Record with compact constructor
│   │   ├── Shape.java                  # Sealed interface
│   │   ├── ShapeCalculator.java        # Exhaustive pattern matching switch
│   │   └── Triangle.java               # Record with triangle inequality validation
│   └── ModernJavaLab.java              # Master runner & verification
└── README.md
```

---

## 🚀 Key Features Implemented
1. **Sealed Interface + Records:**
   - `Shape` permits `Circle`, `Rectangle`, `Triangle`.
   - Compact constructors with validation.
2. **Exhaustive Pattern Matching Switch:**
   - Compiler-verified exhaustiveness without `default` branch.
   - Guarded patterns (`when` clause).
3. **Multiline Text Blocks:**
   - Clean invoice notification email templates using Text Blocks and `.formatted()`.
4. **Immutable Collections & Modern Strings:**
   - Verified `UnsupportedOperationException` on `List.of` and `Map.of`.
   - Sanitized input using `strip()` and `isBlank()`.

---

## ⚙️ Compilation and Execution
```bash
# Navigate to directory
cd "C:\Program Files\Java\jdk-21\bin\hcl\HCL-daily-tasks\HCL-Daily-Tasks\daily-tasks\day-17-java-9-21-features\src"

# Compile all classes
javac -d ../bin shape/*.java invoice/*.java input/*.java collection/*.java ModernJavaLab.java

# Run ModernJavaLab
java -cp ../bin ModernJavaLab
```
