package com.tuana9a.learn.java.servlet.utils;

import java.util.Arrays;

public class HttpUtils {
    private static final HttpUtils instance = new HttpUtils();

    private HttpUtils() {

    }

    public static HttpUtils getInstance() {
        return instance;
    }

    //EXPLAIN: check xem trong tất cả giá trị của 1 header có chứa checkMatch
    public boolean checkHeaderMatchValue(String matchHeader, String checkMatch) {
        String[] matchValues = matchHeader.split("\\s*,\\s*");
        Arrays.sort(matchValues);
        return Arrays.binarySearch(matchValues, checkMatch) > -1
                || Arrays.binarySearch(matchValues, "*") > -1;
    }

    public boolean checkHeaderAccept(String acceptHeader, String checkAccept) {
        String[] acceptValues = acceptHeader.split("\\s*([,;])\\s*");
        Arrays.sort(acceptValues);
        return Arrays.binarySearch(acceptValues, checkAccept) > -1
                || Arrays.binarySearch(acceptValues, checkAccept.replaceAll("/.*$", "/*")) > -1
                //EXPLAIN dòng trên và dưới lần lượt tìm xem trong acceptValues có "/*" hay "*/*" không
                || Arrays.binarySearch(acceptValues, "*/*") > -1;
    }
}
