package ru.job4j.srp;

import lombok.AllArgsConstructor;

import java.util.function.Predicate;

@AllArgsConstructor
public class ReportAccounting implements Report {
    private final static String SEPARATOR = System.lineSeparator();
    private static final double CURRENT_RATE = 76.45;
    private Store store;

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary (USD)").append(SEPARATOR);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() / CURRENT_RATE).append(";")
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
