import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Day 19 Deliverable: Database Runner & Query Verification Engine
 * Executes SQLite in-memory / local verification to prove scripts run cleanly from scratch.
 */
public class DatabaseRunner {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("  HCL Day 19: CRUD Operations - SQL Script Runner & Verification          ");
        System.out.println("==========================================================================\n");

        System.out.println("Validating schema.sql, seed.sql, queries.sql from scratch using Python/SQLite engine...");
        
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
            System.out.println("\nProcess exited with status code: " + exitCode);
            if (exitCode == 0) {
                System.out.println("\n==========================================================================");
                System.out.println("  Done When Validation: Scripts run cleanly from scratch: PASSED!         ");
                System.out.println("==========================================================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
