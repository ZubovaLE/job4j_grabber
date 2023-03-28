package ru.job4j.srp;

import lombok.AllArgsConstructor;

import java.util.function.Predicate;

@AllArgsConstructor
public class HTMLReport implements Report {
    private final static String SEPARATOR = System.lineSeparator();
    private Store store;

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>").append(SEPARATOR)
                .append("<html>").append(SEPARATOR)
                .append("<head>").append(SEPARATOR)
                .append("<meta charset=\"utf-8\">").append(SEPARATOR)
                .append("<title>HTML отчёт</title>").append(SEPARATOR)
                .append("</head>").append(SEPARATOR)
                .append("<body>").append(SEPARATOR)
                .append("<table>")
                .append("<tr><th>Name</th><th>Hired</th>")
                .append("<th>Fired</th><th>Salary</th></tr>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr><td>")
                    .append(employee.getName()).append("</td>").append("<td>")
                    .append(employee.getFired()).append("</td>").append("<td>")
                    .append(employee.getHired()).append("</td>").append("<td>")
                    .append(employee.getSalary()).append("</td></tr>")
                    .append("</table></body></html>")
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
