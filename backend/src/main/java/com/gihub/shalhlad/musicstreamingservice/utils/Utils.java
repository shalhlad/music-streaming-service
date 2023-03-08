package com.gihub.shalhlad.musicstreamingservice.utils;

public class Utils {
    private static final String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean isEmail(String string) {
        return string.matches(EMAIL_REGEXP);
    }

}
