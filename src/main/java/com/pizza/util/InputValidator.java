package com.pizza.util;

import com.pizza.model.Size;

public class InputValidator {

    public static String normalizeSize(String input) {
        if (input == null) {
            return null;
        }

        String value = input.trim().toLowerCase();

        if (value.equals("s") || value.equals("small")) {
            return Size.SMALL;
        } else if (value.equals("m") || value.equals("medium")) {
            return Size.MEDIUM;
        } else if (value.equals("l") || value.equals("large")) {
            return Size.LARGE;
        }

        return null; // invalid
    }

    public static int parseIntOrMinusOne(String input) {
        if (input == null) {
            return -1;
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
