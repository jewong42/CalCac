package com.jewong.calcac.common;

public final class StringUtils {

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isNullOrBlank(String string) {
        return string == null || string.length() == 0;
    }

}