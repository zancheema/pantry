package com.zancheema.android.pantry.common;

public class StringUtils {
    public static boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean anyBlank(String... strs) {
        for (String s : strs) {
            if (isBlank(s)) return true;
        }
        return false;
    }
}
