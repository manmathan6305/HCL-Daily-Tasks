import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Day 21 Deliverable: Constraint Validator & EXPLAIN Comparison Engine
 * Executes test_runner.py to prove every constraint rejects bad data
 * and captures EXPLAIN before/after composite index creation.
 */
public class ConstraintValidatorRunner {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("  HCL Day 21: Views, Indexing & Constraints - Test Engine                 ");
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
                System.out.println("  Done When Validation: Every constraint tested with a failing insert: PASS! ");
                System.out.println("==========================================================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
