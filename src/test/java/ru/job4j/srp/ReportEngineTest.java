package ru.job4j.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ReportEngineTest {

    @Test
    public void whenHTMLReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report reportEngine = new ReportEngine(store);
        StringBuilder expected = new StringBuilder()
                .append("<!DOCTYPE HTML><html><head>")
                .append("<title>HTML отчёт.</title></head>")
                .append("<body><table>")
                .append("<tr><th>Name</th><th>Hired</th>")
                .append("<th>Fired</th><th>Salary</th></tr>")
                .append("<tr><td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td></tr>")
                .append("</table></body></html>")
                .append(System.lineSeparator());
        assertEquals(expected.toString(), reportEngine.generate(em -> true));
    }
}