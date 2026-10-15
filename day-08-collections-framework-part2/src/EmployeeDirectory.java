import model.Department;
import model.Employee;
import service.EmployeeDirectoryService;
import service.MissingHashCodeBugDemo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Deliverable Application: EmployeeDirectory.java
 *
 * Demonstrates Collections Framework - Part 2:
 * - HashMap O(1) lookups
 * - Frequency count and rollup with Map.merge()
 * - TreeMap sorted departmental reporting
 * - Comparable natural ordering (by ID)
 * - Top 5 salary ranking with Comparator.comparing().reversed().thenComparing()
 * - Missing-hashCode bug demonstration and fix
 * - Implements project functional requirements FR4 - FR6
 */
public class EmployeeDirectory {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("  HCL Day 8: Collections Framework - Employee Directory     ");
        System.out.println("============================================================");

        EmployeeDirectory app = new EmployeeDirectory();
        app.runWalkthrough();
    }

    public void runWalkthrough() {
        // 1. Run Missing-hashCode Bug Demo and Fix
        MissingHashCodeBugDemo.runDemo();

        // 2. Initialize Service and Seed Sample Workforce
        EmployeeDirectoryService service = new EmployeeDirectoryService();
        seedSampleData(service);

        // 3. FR4: Map Lookups
        System.out.println("\n============================================================");
        System.out.println(" [FR4] HASHMAP O(1) LOOKUPS & FREQUENCY COUNT VIA MERGE");
        System.out.println("============================================================");

        int lookupId = 104;
        Optional<Employee> empOpt = service.findById(lookupId);
        System.out.printf("Querying employee by ID=%d: %n", lookupId);
        empOpt.ifPresentOrElse(
                e -> System.out.println("   Found: " + e),
                () -> System.out.println("   Employee not found.")
        );

        System.out.println("\nDepartment Headcount Rollup (computed via Map.merge):");
        Map<Department, Integer> headcounts = service.getDepartmentHeadcounts();
        Map<Department, Double> payrolls = service.getDepartmentTotalPayroll();

        headcounts.forEach((dept, count) -> {
            double totalPayroll = payrolls.getOrDefault(dept, 0.0);
            double avgSalary = count > 0 ? totalPayroll / count : 0.0;
            System.out.printf("   - %-24s: %2d employees | Total: $%,11.2f | Avg: $%,10.2f%n",
                    dept.getDisplayName(), count, totalPayroll, avgSalary);
        });

        // 4. FR5: TreeMap Department Report
        System.out.println("\n============================================================");
        System.out.println(" [FR5] TREEMAP SORTED DEPARTMENTAL ANALYTICS REPORT");
        System.out.println("============================================================");
        System.out.println("Departments sorted in lexicographical order via TreeMap key structure:");

        Map<String, List<Employee>> deptReport = service.generateSortedDepartmentReport();
        deptReport.forEach((deptName, members) -> {
            System.out.printf("%n-- Department: %s (%d team members, sorted by ID) --%n", deptName, members.size());
            for (Employee e : members) {
                System.out.println("   " + e);
            }
        });

        // 5. FR6: Top 5 Salary Comparator Chain
        System.out.println("\n============================================================");
        System.out.println(" [FR6] TOP 5 HIGHEST-PAID EMPLOYEES (COMPARATOR CHAIN)");
        System.out.println("============================================================");
        System.out.println("Chain: Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getName):");

        List<Employee> top5 = service.getTop5HighestPaidEmployees();
        int rank = 1;
        for (Employee e : top5) {
            System.out.printf("   #%d -> %s%n", rank++, e);
        }

        // 6. Secondary Comparator Chain: Department ASC -> Experience DESC
        System.out.println("\n============================================================");
        System.out.println(" [FR6] SENIORITY REPORT (DEPT ASC -> EXPERIENCE DESC)");
        System.out.println("============================================================");
        List<Employee> seniorityList = service.getEmployeesSortedByDeptAndExperience();
        for (Employee e : seniorityList) {
            System.out.println("   " + e);
        }

        System.out.println("\n[SUCCESS] Day 8 Daily Hands-on Task & Project FR4-FR6 Completed Successfully!");
    }

    private static void seedSampleData(EmployeeDirectoryService service) {
        List<Employee> seedList = Arrays.asList(
                new Employee(101, "Alice Johnson", Department.ENGINEERING, 145000.00, 8),
                new Employee(102, "Bob Smith", Department.ENGINEERING, 115000.00, 4),
                new Employee(103, "Catherine Zhang", Department.DATA_SCIENCE, 160000.00, 7),
                new Employee(104, "David Kumar", Department.PRODUCT, 138000.00, 6),
                new Employee(105, "Elena Rostova", Department.FINANCE, 128000.00, 5),
                new Employee(106, "Franklin Wright", Department.MARKETING, 105000.00, 4),
                new Employee(107, "Grace Hopper", Department.ENGINEERING, 185000.00, 12),
                new Employee(108, "Hannah Abbott", Department.HUMAN_RESOURCES, 92000.00, 3),
                new Employee(109, "Ian Malcolm", Department.DATA_SCIENCE, 172000.00, 9),
                new Employee(110, "Julia Roberts", Department.PRODUCT, 155000.00, 8),
                new Employee(111, "Kevin Bacon", Department.MARKETING, 118000.00, 6),
                new Employee(112, "Laura Croft", Department.ENGINEERING, 145000.00, 7)
        );
        service.bulkAddEmployees(seedList);
    }
}
