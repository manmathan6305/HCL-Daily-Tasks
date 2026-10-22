# The 6-Point AI Code Review Checklist

Before integrating any AI-generated Java code into your project, systematically verify:

## 1. Encapsulation & Defensive Copies
- Never return mutable collections directly. Wrap them in `Collections.unmodifiableList()` or `List.copyOf()`.
- Never assign constructor collection arguments directly; make a defensive copy: `new ArrayList<>(incomingList)`.

## 2. The `equals()` & `hashCode()` Contract
- Verify both methods are overridden if the class will be used in Sets, Map keys, or compared in tests.
- Ensure the fields used are consistent and ideally immutable.

## 3. Preconditions & Null Safety
- Verify every constructor and public method guards against `null` arguments using `Objects.requireNonNull()`.

## 4. Algorithmic Complexity & Collection Choice
- Look for naive loops searching Lists. Replace with `HashMap` or `HashSet` when lookups are frequent.

## 5. Exception Handling Integrity
- Ensure catch blocks do not silently swallow exceptions or log and continue as if nothing happened.
- Replace generic `catch (Exception e)` with specific catch blocks or domain exceptions.

## 6. Generics & Type Safety
- Verify no raw types (`List` instead of `List<Student>`).
- Ensure no unchecked casts (`(T) object`) without `@SuppressWarnings` and thorough rationale.
