package ru.job4j.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ReportAccountingTest {
    private final static String SEPARATOR = System.lineSeparator();

    @Test
    void whenGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report accounting = new ReportAccounting(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary (USD)")
                .append(SEPARATOR)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() / 76.45).append(";")
                .append(SEPARATOR);
        assertEquals(expected.toString(), accounting.generate(em -> true));
    }
}