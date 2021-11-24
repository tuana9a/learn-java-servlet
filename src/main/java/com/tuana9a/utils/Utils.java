package com.tuana9a.utils;

public class Utils {
    private static final Utils instance = new Utils();

    private Utils() {

    }

    public static Utils getInstance() {
        return instance;
    }

    public String snakeToCamel(String input) {
        String regex = "_[a-z]";
        for (int index = input.indexOf('_'); index != -1; index = input.indexOf('_')) {
            input = input.replaceFirst(regex, String.valueOf(Character.toUpperCase(input.charAt(index + 1))));
        }
        return input;
    }

    public String camelToSnake(String input) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return input.replaceAll(regex, replacement).toLowerCase();
    }

    public long getLongFromString(String input, int begin, int end) {
        String substring = input.substring(begin, end);
        return (substring.length() > 0) ? Long.parseLong(substring) : -1;
    }

}
