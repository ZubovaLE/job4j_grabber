package ru.job4j.srp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class XMLReportTest {

    @Test
    @DisplayName("Test generate when add two employees")
    void generate() {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2018, Calendar.FEBRUARY, 2);;
        Employee workerOne = new Employee("WorkerOne", now, now, 100);
        Employee workerTwo = new Employee("WorkerTwo", now, now, 150);
        store.add(workerOne);
        store.add(workerTwo);
        XMLReport report = new XMLReport(store);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<employee name=\"WorkerOne\">\n" +
                "    <hired>2018-02-02T00:00:00+03:00</hired>\n" +
                "    <fired>2018-02-02T00:00:00+03:00</fired>\n" +
                "    <salary>100.0</salary>\n" +
                "</employee>\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<employee name=\"WorkerTwo\">\n" +
                "    <hired>2018-02-02T00:00:00+03:00</hired>\n" +
                "    <fired>2018-02-02T00:00:00+03:00</fired>\n" +
                "    <salary>150.0</salary>\n" +
                "</employee>\n";
        assertEquals(expected, report.generate(em -> true));
    }
}