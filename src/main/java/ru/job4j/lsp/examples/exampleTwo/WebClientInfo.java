package ru.job4j.lsp.examples.exampleTwo;

import java.time.LocalDate;

public class WebClientInfo extends ClientInfo {
    @Override
    public LocalDate getBirthDate(int year, int month, int day) {
        LocalDate result = LocalDate.of(year, month, day);
        if (result.isBefore(LocalDate.of(1900, 1, 1))) {
            throw new IllegalArgumentException("Invalid birth date");
        }
        return result;
    }
}
