# Comparable vs Comparator in Modern Java

## 1. Fundamental Differences

| Dimension | `Comparable<T>` | `Comparator<T>` |
| :--- | :--- | :--- |
| **Package** | `java.lang` | `java.util` |
| **Location** | Defined inside the entity class | Defined in external strategy classes / lambdas |
| **Method** | `int compareTo(T other)` | `int compare(T o1, T o2)` |
| **Purpose** | Defines single **natural ordering** | Defines multiple **custom sorting strategies** |
| **Class Modification** | Requires modifying class source code | No modification needed (works on third-party classes) |

---

## 2. Java 8+ Comparator Chaining Combinators

Modern Java provides fluent combinators on the `Comparator` interface:

```java
// 1. Sort by salary descending
Comparator<Employee> bySalaryDesc = Comparator.comparing(Employee::getSalary).reversed();

// 2. Multi-tier Comparator Chain:
// Highest salary first -> Alphabetical name tie-breaker -> ID tie-breaker
Comparator<Employee> topPayComparator = Comparator.comparing(Employee::getSalary)
        .reversed()
        .thenComparing(Employee::getName)
        .thenComparing(Employee::getId);

// 3. Department ascending, then seniority descending:
Comparator<Employee> deptSeniorityComparator = Comparator.comparing(Employee::getDepartment)
        .thenComparing(Comparator.comparing(Employee::getExperienceYears).reversed());
```
