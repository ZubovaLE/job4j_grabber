package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HabrCareerDateTimeParser implements DateTimeParser {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER1 = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    @Override
    public LocalDateTime parse(String parse) {
        String[] date = parse.split("\\+");
        return LocalDateTime.parse(date[0], DATE_TIME_FORMATTER1);
    }
}