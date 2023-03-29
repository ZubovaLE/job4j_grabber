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
                .append("<table>").append(SEPARATOR)
                .append("<tr>").append(SEPARATOR)
                .append("<th>Name</th>").append(SEPARATOR)
                .append("<th>Hired</th>").append(SEPARATOR)
                .append("<th>Fired</th>").append(SEPARATOR)
                .append("<th>Salary</th>").append(SEPARATOR)
                .append("</tr>").append(SEPARATOR);
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr><").append(SEPARATOR)
                    .append("td>").append(employee.getName()).append("</td>").append(SEPARATOR)
                    .append("<td>").append(employee.getFired()).append("</td>").append(SEPARATOR)
                    .append("<td>").append(employee.getHired()).append("</td>").append(SEPARATOR)
                    .append("<td>").append(employee.getSalary()).append("</td>").append(SEPARATOR)
                    .append("</tr>").append(SEPARATOR)
                    .append("</table>").append(SEPARATOR)
                    .append("</body>").append(SEPARATOR)
                    .append("</html>").append(SEPARATOR);
        }
        return text.toString();
    }
}
