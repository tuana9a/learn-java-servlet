package com.tuana9a;

import com.tuana9a.config.AppConfig;
import org.junit.Test;

public class ExplorerTests {
    public String makeUrl(String folderPath, String fileName) {
        AppConfig config = AppConfig.getInstance();
        return config.EXPLORER_PREFIX + (folderPath.equals("/") ? "" : folderPath) + "/" + fileName;
    }

    @Test
    public void testMakeUrl() {
        System.out.println(makeUrl("/", "test"));
        System.out.println(makeUrl("/", "test.txt"));
        System.out.println(makeUrl("/test", "test"));
        System.out.println(makeUrl("/test", "test.txt"));
    }

}
