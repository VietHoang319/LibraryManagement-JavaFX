package com.example.librarymanagement.datetime;

import java.time.LocalDateTime;

public class DateTimeFormatter {
    private final static String PATTERN_DATETIME = "ddMMyyHHmmss";
    public static String formatDateTime(LocalDateTime dateTime) {
        java.time.format.DateTimeFormatter formater = java.time.format.DateTimeFormatter.ofPattern(PATTERN_DATETIME);
        return formater.format(dateTime);
    }
}
