package ru.job4j.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ReportHRTest {
    private static final String SEPARATOR = System.lineSeparator();

    @Test
    void generate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerOne = new Employee("WorkerOne", now, now, 100);
        Employee workerTwo = new Employee("WorkerTwo", now, now, 150);
        Employee workerThree = new Employee("WorkerThree", now, now, 90);
        store.add(workerOne);
        store.add(workerTwo);
        store.add(workerThree);
        Report engine = new ReportHR(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary")
                .append(SEPARATOR)
                .append(workerTwo.getName()).append(";")
                .append(workerTwo.getSalary()).append(";")
                .append(SEPARATOR)
                .append(workerOne.getName()).append(";")
                .append(workerOne.getSalary()).append(";")
                .append(SEPARATOR)
                .append(workerThree.getName()).append(";")
                .append(workerThree.getSalary()).append(";")
                .append(SEPARATOR);
        assertEquals(expected.toString(), engine.generate(em -> true));
    }
}