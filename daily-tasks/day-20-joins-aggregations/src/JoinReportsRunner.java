import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Day 20 Deliverable: Joins & Aggregations Verification Engine
 * Executes test_runner.py to verify correct row counts and zero Cartesian product duplicates.
 */
public class JoinReportsRunner {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("  HCL Day 20: Joins & Aggregations - Verification Engine                  ");
        System.out.println("==========================================================================\n");

        try {
            ProcessBuilder pb = new ProcessBuilder("python", "test_runner.py");
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  " + line);
            }
            int exitCode = p.waitFor();
            if (exitCode == 0) {
                System.out.println("\n==========================================================================");
                System.out.println("  Done When Validation: Correct row counts; no duplicates: PASSED!        ");
                System.out.println("==========================================================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
