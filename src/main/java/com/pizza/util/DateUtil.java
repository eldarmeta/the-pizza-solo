package com.pizza.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter FILE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public static String formatForDisplay(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_FORMATTER);
    }

    public static String formatForFile(LocalDateTime dateTime) {
        return dateTime.format(FILE_FORMATTER);
    }
}
