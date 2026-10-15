package lab;

/**
 * Experiment 5: The Integer Cache == Pitfall
 * Demonstrates the subtle JVM bug where == comparison on boxed Integers
 * succeeds for values between -128 and 127, but fails for values outside that range.
 */
public class IntegerCachePitfall {

    public static class CacheTestResult {
        public final int val1;
        public final int val2;
        public final boolean identityEquals; // ==
        public final boolean logicalEquals;  // .equals()

        public CacheTestResult(int val1, int val2, boolean identityEquals, boolean logicalEquals) {
            this.val1 = val1;
            this.val2 = val2;
            this.identityEquals = identityEquals;
            this.logicalEquals = logicalEquals;
        }
    }

    public static CacheTestResult testEquality(int value) {
        Integer a = value; // Autoboxing invokes Integer.valueOf(value)
        Integer b = value;
        return new CacheTestResult(value, value, a == b, a.equals(b));
    }

    public static void runDemo() {
        System.out.println("\n============================================================");
        System.out.println(" [EXP 5] INTEGER CACHE == PITFALL (RANGE [-128, 127])");
        System.out.println("============================================================");

        CacheTestResult withinCache = testEquality(127);
        CacheTestResult outsideCache = testEquality(128);
        CacheTestResult negativeWithin = testEquality(-128);
        CacheTestResult negativeOutside = testEquality(-129);

        System.out.println("1. Testing Integer a = 127, b = 127 (Within Cache [-128, 127]):");
        System.out.printf("   - a == b       : %s (References Point to SAME cached object in IntegerCache)%n", withinCache.identityEquals);
        System.out.printf("   - a.equals(b)  : %s%n", withinCache.logicalEquals);

        System.out.println("\n2. Testing Integer c = 128, d = 128 (Outside Cache Range):");
        System.out.printf("   - c == d       : %s (PITFALL: False! Two DISTINCT heap objects allocated!)%n", outsideCache.identityEquals);
        System.out.printf("   - c.equals(d)  : %s (Correct logical equality)%n", outsideCache.logicalEquals);

        System.out.println("\n3. Testing Negative Edge Cases (-128 vs -129):");
        System.out.printf("   - (-128 == -128) : %s (Cached)%n", negativeWithin.identityEquals);
        System.out.printf("   - (-129 == -129) : %s (Distinct heap allocations!)%n", negativeOutside.identityEquals);

        System.out.println("\nGolden Rule: NEVER compare boxed wrapper objects using '=='. Always use '.equals()'.");
    }
}
