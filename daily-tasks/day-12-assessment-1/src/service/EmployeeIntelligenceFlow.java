package service;

import model.EnterpriseEmployee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeIntelligenceFlow {
    private final Map<String, EnterpriseEmployee> employeeMap = new HashMap<>();

    public void registerEmployee(EnterpriseEmployee emp) {
        employeeMap.put(emp.getId(), emp);
    }

    public Optional<EnterpriseEmployee> getById(String id) {
        return Optional.ofNullable(employeeMap.get(id));
    }

    public Map<String, Integer> getDepartmentHeadcounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (EnterpriseEmployee e : employeeMap.values()) {
            counts.merge(e.getDepartment(), 1, Integer::sum);
        }
        return counts;
    }

    public Map<String, List<EnterpriseEmployee>> generateSortedDepartmentReport() {
        Map<String, List<EnterpriseEmployee>> report = new TreeMap<>();
        for (EnterpriseEmployee e : employeeMap.values()) {
            report.computeIfAbsent(e.getDepartment(), k -> new ArrayList<>()).add(e);
        }
        for (List<EnterpriseEmployee> list : report.values()) {
            Collections.sort(list); // Natural sort by ID
        }
        return report;
    }

    public List<EnterpriseEmployee> getTopEarners(int limit) {
        Comparator<EnterpriseEmployee> comp = Comparator.comparing(EnterpriseEmployee::getSalary)
                .reversed()
                .thenComparing(EnterpriseEmployee::getName);
        return employeeMap.values().stream()
                .sorted(comp)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public int size() {
        return employeeMap.size();
    }
}
