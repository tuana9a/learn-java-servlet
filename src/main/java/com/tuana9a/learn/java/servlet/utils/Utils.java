package com.tuana9a.learn.java.servlet.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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

    public <T> T getObject(ResultSet rs, Class<T> classs)
            throws InstantiationException, IllegalAccessException, SQLException {
        Field[] fields = classs.getDeclaredFields();
        Utils utils = Utils.getInstance();
        T object = classs.newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldSnakeCase = utils.camelToSnake(field.getName());
            field.set(object, rs.getObject(fieldSnakeCase));
        }
        return object;
    }

    public <T> List<T> getList(ResultSet rs, Class<T> _class)
            throws SQLException, InstantiationException, IllegalAccessException {
        List<T> data = new LinkedList<>();
        while (rs.next()) {
            data.add(this.getObject(rs, _class));
        }
        return data;
    }

}
