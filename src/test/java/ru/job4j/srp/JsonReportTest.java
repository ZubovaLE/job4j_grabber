package ru.job4j.srp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class JsonReportTest {

    @Test
    @DisplayName("Test generate when add two employees")
    void generate() {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2018, Calendar.FEBRUARY, 2);
        Employee workerOne = new Employee("WorkerOne", now, now, 100);
        Employee workerTwo = new Employee("WorkerTwo", now, now, 150);
        store.add(workerOne);
        store.add(workerTwo);
        JsonReport report = new JsonReport(store);
        StringBuilder expected = new StringBuilder();
        expected.append("[{")
                .append("\"name\":\"WorkerOne\",")
                .append("\"hired\":{\"year\":2018,\"month\":1,\"dayOfMonth\":2,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"fired\":{\"year\":2018,\"month\":1,\"dayOfMonth\":2,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"salary\":100.0},")
                .append("{\"name\":\"WorkerTwo\",")
                .append("\"hired\":{\"year\":2018,\"month\":1,\"dayOfMonth\":2,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"fired\":{\"year\":2018,\"month\":1,\"dayOfMonth\":2,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                .append("\"salary\":150.0")
                .append("}]");
        assertEquals(expected.toString(), report.generate(em -> true));
    }
}