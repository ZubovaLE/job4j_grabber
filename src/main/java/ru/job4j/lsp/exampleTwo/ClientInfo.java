package ru.job4j.lsp.exampleTwo;

import java.time.LocalDate;

public class ClientInfo {
    public LocalDate getBirthDate(int year, int month, int day) {
        validate(year, month, day);
        LocalDate result = LocalDate.of(year, month, day);
        if (result.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid birth date");
        }
        return result;
    }

    protected void validate(int year, int month, int day) {
        if (year < 0 || month < 0 || month > 12 || day < 0 || day > 31) {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }
}
