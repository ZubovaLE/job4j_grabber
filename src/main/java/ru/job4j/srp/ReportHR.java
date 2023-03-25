package ru.job4j.srp;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ReportHR implements Report {
    private final static String SEPARATOR = System.lineSeparator();
    private Store store;

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter).stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()) * (-1))
                .collect(Collectors.toList());
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary").append(SEPARATOR);
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();

    }
}
