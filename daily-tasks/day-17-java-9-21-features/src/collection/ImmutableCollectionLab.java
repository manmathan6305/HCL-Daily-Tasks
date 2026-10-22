package collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Demonstrates Java 9+ Immutable Factory Collections (List.of, Set.of, Map.of).
 * Proves that modifications throw UnsupportedOperationException at runtime.
 */
public class ImmutableCollectionLab {

    public static void verifyImmutability() {
        var immutableList = List.of("Java 11", "Java 17", "Java 21");
        var immutableSet = Set.of("SEALED", "RECORD", "PATTERN_MATCHING");
        var immutableMap = Map.of("version", "21", "lts", "true");

        System.out.println("  Immutable List : " + immutableList + " (Class: " + immutableList.getClass().getSimpleName() + ")");
        System.out.println("  Immutable Set  : " + immutableSet + " (Class: " + immutableSet.getClass().getSimpleName() + ")");
        System.out.println("  Immutable Map  : " + immutableMap + " (Class: " + immutableMap.getClass().getSimpleName() + ")");

        // Attempt mutation to verify UnsupportedOperationException
        try {
            System.out.print("  Attempting immutableList.add(\"Java 22\")... ");
            immutableList.add("Java 22");
        } catch (UnsupportedOperationException e) {
            System.out.println("[SUCCESSFULLY CAUGHT UnsupportedOperationException]");
            System.out.println("  >> Verified: List.of() produces an unmodifiable, structural invariant collection!");
        }

        try {
            System.out.print("  Attempting immutableMap.put(\"newKey\", \"val\")... ");
            immutableMap.put("newKey", "val");
        } catch (UnsupportedOperationException e) {
            System.out.println("[SUCCESSFULLY CAUGHT UnsupportedOperationException]");
            System.out.println("  >> Verified: Map.of() disallows modification!");
        }
    }
}
