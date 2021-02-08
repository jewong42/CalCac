package com.jewong.calcac.common;

public final class StringUtils {

    public static boolean isNullOrBlank(String string) {
        return string == null || string.length() == 0;
    }

    public static String formatLength(String string, int maxLength) {
        if (string.length() <= maxLength) return string;
        return string.substring(0, maxLength) + "...";
    }

}