package com.cyx.creater.utils;

public class Console {

    public static void log(String... logs) {
        System.out.println(logsToString(logs));
    }

    public static void err(String... logs) {
        System.err.println(logsToString(logs));
    }

    private static String logsToString(String... logs) {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < logs.length; i++) {
            sb.append(logs[i]).append("\t");
        }
        return sb.toString();
    }
}
