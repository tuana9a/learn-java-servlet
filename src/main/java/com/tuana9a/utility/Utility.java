package com.tuana9a.utility;

import com.google.gson.Gson;

import java.io.BufferedReader;

public class Utility {
    private static final Gson  gson = new Gson();

    public static <T> T toObjectFromJson(String json, Class<T> className) {
        return gson.fromJson(json,className);
    }

    public static <T> T toObjectFromJson(BufferedReader reader, Class<T> className) {
        return gson.fromJson(reader,className);
    }



    public static String snakeToCamel(String input) {
        String regex = "_[a-z]";
        for (int index = input.indexOf('_'); index != -1; index = input.indexOf('_')) {
            input = input.replaceFirst(regex, String.valueOf(Character.toUpperCase(input.charAt(index + 1))));
        }
        return input;
    }

    public static String camelToSnake(String input) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return input.replaceAll(regex, replacement).toLowerCase();
    }
}
