package com.example.librarymanagement.datetime;

import java.time.LocalDateTime;

public class DateTimeFormatter {
    private final static String PATTERN_DATETIME_CREATE_ID = "ddMMyyHHmmss";
    private final static String PATTERN_DATETIME = "dd-MM-yyyy HH:mm:ss";

    public static String getPatternDatetimeCreateId() {
        return PATTERN_DATETIME_CREATE_ID;
    }

    public static String getPatternDatetime() {
        return PATTERN_DATETIME;
    }

    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        java.time.format.DateTimeFormatter formater = java.time.format.DateTimeFormatter.ofPattern(pattern);
        return formater.format(dateTime);
    }

    public static LocalDateTime parseDatetime(String dateTime, String pattern) {
        java.time.format.DateTimeFormatter formater = java.time.format.DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTime, formater);
    }

    public static String convertDatetime(String dateTime, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        java.time.format.DateTimeFormatter formater = java.time.format.DateTimeFormatter.ofPattern(pattern);
        return formater.format(localDateTime);
    }
}
