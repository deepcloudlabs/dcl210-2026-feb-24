package com.example.util;

public final class StringUtil {
    private StringUtil() {}

    public static boolean isBlank(String s) {
        return s == null || s.trim().length() == 0;
    }
}
