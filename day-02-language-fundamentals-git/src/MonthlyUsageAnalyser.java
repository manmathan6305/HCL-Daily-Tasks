public class MonthlyUsageAnalyser {
    public static void main(String[] args) {
        System.out.println("Monthly Usage Analyser");

        final int baseFee = 50;
        int[] usage = { 10, 145, 110, 160, 200, 210, 190, 180, 150, 130, 140, 125 };

        int total = 0;
        int max = usage[0];
        int min = usage[0];

        for (int u : usage) {
            total += u;
            if (u > max) max = u;
            if (u < min) min = u;
        }

        double average = (double) total / usage.length;
        String grade = (average > 150) ? "High Usage" : "Normal Usage";

        System.out.println("Total: " + total);
        System.out.println("Average: " + String.format("%.2f", average));
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Grade: " + grade);

        int maxInt = Integer.MAX_VALUE;
        System.out.println("Max int: " + maxInt);
        System.out.println("Overflow: " + (maxInt + 1)); 
        System.out.println("Corrected: " + ((long) maxInt + 1));
        
        int[][] houseUsage = {
            { 100, 110, 105 },
            { 200, 215, 210 },
            { 80, 85, 90 }
        };

        for (int i = 0; i < houseUsage.length; i++) {
            int houseTotal = 0;
            for (int j = 0; j < houseUsage[i].length; j++) {
                houseTotal += houseUsage[i][j];
            }
            System.out.println("House " + (i + 1) + " Total: " + houseTotal);
        }
    }
}
