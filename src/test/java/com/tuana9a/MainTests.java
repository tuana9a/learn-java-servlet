package com.tuana9a;

import com.tuana9a.config.AppConfig;
import com.tuana9a.utils.LogUtils;
import org.junit.Test;

public class MainTests {

    @Test
    public void test() {
        LogUtils.getLogger().info("Hello World!");
        AppConfig config = AppConfig.getInstance();
        config.load();
        System.out.println(config);
    }

}

