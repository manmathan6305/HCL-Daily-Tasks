# Modern Java Syntax Updates (Java 9 - 21)

## 1. Local-Variable Type Inference (`var`)
- Introduced in Java 10.
- Guidelines:
  - Use when the type is obvious on the right side: `var list = new ArrayList<String>();`
  - Avoid when it obscures business meaning: `var x = process();` (Unclear what `x` is).

---

## 2. Modern String APIs
- `isBlank()`: Checks if string is empty or contains only whitespace codepoints.
- `strip()`: Unicode-aware whitespace trimmer (replaces legacy ASCII-only `trim()`).
- `lines()`: Returns a `Stream<String>` split by line terminators (`\n`, `\r\n`).
- `repeat(n)`: Multiplies string `n` times.
- `formatted()`: Instance method shortcut for `String.format()`.
