package com.example;

import java.text.MessageFormat;

public class Utility {

    public Utility() {
    }

    public static void outPutConsoleMessage(String message) {
        System.out.println(message);
    }

    public static void outputErrorMessage(String message) {
        SystemValue sysVal = new SystemValue();
        System.out.println(MessageFormat.format(sysVal.getErrorDesignCode(), message));
    }
}
