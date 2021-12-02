package com.tuana9a;

import com.tuana9a.config.AppConfig;
import com.tuana9a.utils.JsonUtils;
import com.tuana9a.utils.LogUtils;
import org.junit.Test;

public class MainTests {

    @Test
    public void test() {
        LogUtils.getLogger().info("Hello World!");
        System.out.println(JsonUtils.getInstance().toJson(AppConfig.getInstance()));
    }

    public String makeUrl(String folderPath, String fileName) {
        return "/explorer.exe" + (folderPath.equals("/") ? "" : folderPath) + "/" + fileName;
    }

    @Test
    public void test1() {
        System.out.println(makeUrl("/", "test"));
        System.out.println(makeUrl("/", "test.txt"));
        System.out.println(makeUrl("/test", "test"));
        System.out.println(makeUrl("/test", "test.txt"));
    }

}

