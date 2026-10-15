package service;

import model.Department;
import model.Employee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service providing:
 * - HashMap indexed lookups (O(1))
 * - Frequency counting & payroll rollups via Map.merge()
 * - TreeMap sorted departmental analytics
 * - Dynamic Comparator chaining (comparing().reversed().thenComparing())
 */
public class EmployeeDirectoryService {
    // 1. HashMap for O(1) average lookup by Employee ID
    private final Map<Integer, Employee> employeeIndex = new HashMap<>();

    // 2. LinkedHashMap to track employee onboarding/registration order
    private final Map<Integer, Employee> onboardingHistory = new LinkedHashMap<>();

    public void addEmployee(Employee emp) {
        employeeIndex.put(emp.getId(), emp);
        onboardingHistory.put(emp.getId(), emp);
    }

    public void bulkAddEmployees(Collection<Employee> employees) {
        for (Employee emp : employees) {
            addEmployee(emp);
        }
    }

    /**
     * FR4: Fast O(1) indexed lookup by Employee ID.
     */
    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(employeeIndex.get(id));
    }

    /**
     * Frequency counting using Map.merge():
     * Computes the headcount of employees in each department.
     */
    public Map<Department, Integer> getDepartmentHeadcounts() {
        Map<Department, Integer> countMap = new HashMap<>();
        for (Employee emp : employeeIndex.values()) {
            countMap.merge(emp.getDepartment(), 1, Integer::sum);
        }
        return countMap;
    }

    /**
     * Payroll aggregation using Map.merge():
     * Sums total salary budget expenditure per department.
     */
    public Map<Department, Double> getDepartmentTotalPayroll() {
        Map<Department, Double> payrollMap = new HashMap<>();
        for (Employee emp : employeeIndex.values()) {
            payrollMap.merge(emp.getDepartment(), emp.getSalary(), Double::sum);
        }
        return payrollMap;
    }

    /**
     * FR5: TreeMap Department Report.
     * Generates a sorted departmental report using a TreeMap where keys (Departments)
     * are ordered according to their natural enum order or alphabetical display name.
     */
    public Map<String, List<Employee>> generateSortedDepartmentReport() {
        // TreeMap guarantees keys are sorted alphabetically
        Map<String, List<Employee>> sortedReport = new TreeMap<>();

        for (Employee emp : employeeIndex.values()) {
            String deptName = emp.getDepartment().getDisplayName();
            sortedReport.computeIfAbsent(deptName, k -> new ArrayList<>()).add(emp);
        }

        // Sort employees inside each department by ID (Comparable natural order)
        for (List<Employee> list : sortedReport.values()) {
            Collections.sort(list);
        }

        return sortedReport;
    }

    /**
     * FR6: Top 5 Highest-Paid Employees using Java 8+ Comparator Chain:
     * Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getName)
     */
    public List<Employee> getTop5HighestPaidEmployees() {
        Comparator<Employee> topSalaryComparator = Comparator.comparing(Employee::getSalary)
                .reversed()
                .thenComparing(Employee::getName)
                .thenComparing(Employee::getId);

        return employeeIndex.values().stream()
                .sorted(topSalaryComparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * FR6: Multi-Criteria Custom Report:
     * Sorted by Department (Ascending), then by Experience Years (Descending),
     * then by Salary (Descending).
     */
    public List<Employee> getEmployeesSortedByDeptAndExperience() {
        Comparator<Employee> compositeComparator = Comparator.comparing((Employee e) -> e.getDepartment().getDisplayName())
                .thenComparing(Comparator.comparing(Employee::getExperienceYears).reversed())
                .thenComparing(Comparator.comparing(Employee::getSalary).reversed());

        List<Employee> sortedList = new ArrayList<>(employeeIndex.values());
        sortedList.sort(compositeComparator);
        return sortedList;
    }

    public int getTotalEmployeeCount() {
        return employeeIndex.size();
    }
}
