package input;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates modern String utility methods added in Java 11-15:
 * - isBlank()
 * - strip(), stripLeading(), stripTrailing()
 * - lines()
 * - repeat()
 */
public class UserInputCleaner {

    public static boolean isValidInput(String input) {
        return input != null && !input.isBlank();
    }

    public static String clean(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        // Unicode-aware strip() compared to legacy trim()
        return input.strip();
    }

    public static List<String> extractNonEmptyLines(String multilineText) {
        if (multilineText == null) return List.of();
        return multilineText.lines()
                .map(String::strip)
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
    }

    public static String generateSeparator(char symbol, int count) {
        return String.valueOf(symbol).repeat(count);
    }
}
