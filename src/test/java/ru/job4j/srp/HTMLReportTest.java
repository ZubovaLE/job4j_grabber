package ru.job4j.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class HTMLReportTest {
    private final static String SEPARATOR = System.lineSeparator();

    @Test
    public void whenHTMLReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report htmlReport = new HTMLReport(store);
        StringBuilder expected = new StringBuilder();
        expected.append("<!DOCTYPE html>").append(SEPARATOR)
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
                .append("</tr>").append(SEPARATOR)
                .append("<tr><").append(SEPARATOR)
                .append("td>").append(worker.getName()).append("</td>").append(SEPARATOR)
                .append("<td>").append(worker.getFired()).append("</td>").append(SEPARATOR)
                .append("<td>").append(worker.getHired()).append("</td>").append(SEPARATOR)
                .append("<td>").append(worker.getSalary()).append("</td>").append(SEPARATOR)
                .append("</tr>").append(SEPARATOR)
                .append("</table>").append(SEPARATOR)
                .append("</body>").append(SEPARATOR)
                .append("</html>").append(SEPARATOR);
        assertEquals(expected.toString(), htmlReport.generate(em -> true));
    }
}