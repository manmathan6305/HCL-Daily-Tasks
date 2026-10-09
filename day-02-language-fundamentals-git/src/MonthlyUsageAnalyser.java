public class MonthlyUsageAnalyser {
    public static void main(String[] args) {
        System.out.println("=== Monthly Usage Analyser ===");

        // 1. Constants
        final int BASE_FEE = 50;

        // 2. 1D Array for 12 months of usage
        int[] monthlyUsage = { 120, 145, 110, 160, 200, 210, 190, 180, 150, 130, 140, 125 };

        // 3. Calculate Total, Max, Min
        int totalUsage = 0;
        int maxUsage = monthlyUsage[0];
        int minUsage = monthlyUsage[0];

        for (int usage : monthlyUsage) {
            totalUsage += usage;
            if (usage > maxUsage) maxUsage = usage;
            if (usage < minUsage) minUsage = usage;
        }

        // 4. Safe Average Calculation & Type Casting
        double averageUsage = (double) totalUsage / monthlyUsage.length;

        // 5. Ternary Operator
        String grade = (averageUsage > 150) ? "High Usage" : "Normal Usage";

        System.out.println("Total Usage: " + totalUsage);
        System.out.println("Average Usage: " + String.format("%.2f", averageUsage));
        System.out.println("Max Usage: " + maxUsage);
        System.out.println("Min Usage: " + minUsage);
        System.out.println("Usage Grade: " + grade);

        // 6. Integer Overflow Demonstration
        System.out.println("\n--- Integer Overflow Demonstration ---");
        int maxInt = Integer.MAX_VALUE;
        System.out.println("Max int value: " + maxInt);
        int overflowInt = maxInt + 1;
        System.out.println("Overflowed int (max + 1): " + overflowInt); // Wraps to negative

        // Correcting overflow using long
        long correctSum = (long) maxInt + 1;
        System.out.println("Corrected using long: " + correctSum);

        // 7. 2D Array Demonstration (Usage for 3 houses over 3 months)
        System.out.println("\n--- 2D Array Demonstration (3 Houses, 3 Months) ---");
        int[][] housesUsage = {
            { 100, 110, 105 }, // House 1
            { 200, 215, 210 }, // House 2
            { 80, 85, 90 }     // House 3
        };

        for (int i = 0; i < housesUsage.length; i++) {
            int houseTotal = 0;
            for (int j = 0; j < housesUsage[i].length; j++) {
                houseTotal += housesUsage[i][j];
            }
            System.out.println("House " + (i + 1) + " Total: " + houseTotal);
        }
    }
}
