package ru.job4j.srp;

import lombok.AllArgsConstructor;

import java.util.function.Predicate;

@AllArgsConstructor
public class ReportEngine implements Report {
    private final static String SEPARATOR = System.lineSeparator();
    private Store store;

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE HTML><html><head>")
                .append("<title>HTML отчёт.</title></head>")
                .append("<body><table>")
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
