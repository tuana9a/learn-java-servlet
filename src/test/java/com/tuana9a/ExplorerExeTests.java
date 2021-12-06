package com.tuana9a;

import org.junit.Test;

public class ExplorerExeTests {
    public String makeUrl(String folderPath, String fileName) {
        return "/explorer.exe" + (folderPath.equals("/") ? "" : folderPath) + "/" + fileName;
    }

    @Test
    public void testMakeUrl() {
        System.out.println(makeUrl("/", "test"));
        System.out.println(makeUrl("/", "test.txt"));
        System.out.println(makeUrl("/test", "test"));
        System.out.println(makeUrl("/test", "test.txt"));
    }

}
