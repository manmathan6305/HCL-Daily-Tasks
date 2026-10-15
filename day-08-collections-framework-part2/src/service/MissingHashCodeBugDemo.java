package service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Educational demonstration of the classic "Missing-hashCode Bug" in Java.
 * Demonstrates what happens when equals() is overridden without hashCode(),
 * and how implementing the full hashCode/equals contract restores O(1) retrieval.
 */
public class MissingHashCodeBugDemo {

    /**
     * FLAWED KEY CLASS:
     * Overrides equals() but FAILS to override hashCode().
     * Relies on java.lang.Object's default System.identityHashCode (memory address).
     */
    public static class FlawedEmployeeKey {
        private final int id;
        private final String departmentCode;

        public FlawedEmployeeKey(int id, String departmentCode) {
            this.id = id;
            this.departmentCode = departmentCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FlawedEmployeeKey that)) return false;
            return id == that.id && Objects.equals(departmentCode, that.departmentCode);
        }

        // MISSING hashCode() intentionally!
        @Override
        public String toString() {
            return "FlawedKey[ID=" + id + ", Dept=" + departmentCode + "]";
        }
    }

    /**
     * FIXED KEY CLASS:
     * Properly implements BOTH equals() and hashCode() using Objects.hash().
     */
    public static class FixedEmployeeKey {
        private final int id;
        private final String departmentCode;

        public FixedEmployeeKey(int id, String departmentCode) {
            this.id = id;
            this.departmentCode = departmentCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FixedEmployeeKey that)) return false;
            return id == that.id && Objects.equals(departmentCode, that.departmentCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, departmentCode);
        }

        @Override
        public String toString() {
            return "FixedKey[ID=" + id + ", Dept=" + departmentCode + "]";
        }
    }

    public static void runDemo() {
        System.out.println("============================================================");
        System.out.println(" [BUG DEMO & FIX] MISSING-HASHCODE CONTRACT VIOLATION");
        System.out.println("============================================================");

        // 1. Bug Demonstration
        Map<FlawedEmployeeKey, String> flawedMap = new HashMap<>();
        FlawedEmployeeKey key1 = new FlawedEmployeeKey(101, "ENG");
        flawedMap.put(key1, "Confidential Security Profile - Alice");

        FlawedEmployeeKey lookupKeyFlawed = new FlawedEmployeeKey(101, "ENG");
        boolean areKeysEqual = lookupKeyFlawed.equals(key1);
        String retrievedFlawed = flawedMap.get(lookupKeyFlawed);

        System.out.println("1. Flawed Key Test (equals overridden, hashCode NOT overridden):");
        System.out.println("   - Original Key: " + key1 + " (System.identityHashCode: " + System.identityHashCode(key1) + ")");
        System.out.println("   - Lookup Key:   " + lookupKeyFlawed + " (System.identityHashCode: " + System.identityHashCode(lookupKeyFlawed) + ")");
        System.out.println("   - lookupKey.equals(originalKey): " + areKeysEqual + " (Expected: true)");
        System.out.println("   - flawedMap.get(lookupKey):      " + retrievedFlawed + " (BUG: Returned null!)");
        System.out.println("   -> EXPLANATION: HashMap routed lookupKey to bucket using memory address hash,");
        System.out.println("      missing the bucket where originalKey was stored!");

        // 2. Fix Demonstration
        Map<FixedEmployeeKey, String> fixedMap = new HashMap<>();
        FixedEmployeeKey fixedKey1 = new FixedEmployeeKey(101, "ENG");
        fixedMap.put(fixedKey1, "Confidential Security Profile - Alice");

        FixedEmployeeKey lookupKeyFixed = new FixedEmployeeKey(101, "ENG");
        boolean areFixedKeysEqual = lookupKeyFixed.equals(fixedKey1);
        String retrievedFixed = fixedMap.get(lookupKeyFixed);

        System.out.println("\n2. Fixed Key Test (both equals and hashCode properly overridden):");
        System.out.println("   - Original Key hashCode: " + fixedKey1.hashCode());
        System.out.println("   - Lookup Key hashCode:   " + lookupKeyFixed.hashCode());
        System.out.println("   - lookupKey.equals(originalKey): " + areFixedKeysEqual);
        System.out.println("   - fixedMap.get(lookupKey):       \"" + retrievedFixed + "\" (FIX CONFIRMED!)");
        System.out.println("   -> EXPLANATION: Both instances produce identical hashCodes, routing to identical bucket,");
        System.out.println("      where equals() confirms identity and retrieves the value in O(1) time.");
    }
}
