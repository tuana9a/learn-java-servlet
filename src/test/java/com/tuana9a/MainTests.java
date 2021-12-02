package com.tuana9a;

import com.tuana9a.config.AppConfig;
import com.tuana9a.utils.JsonUtils;
import com.tuana9a.utils.LogUtils;
import org.junit.Test;

public class MainTests {

    @Test
    public void test() {
        LogUtils.getInstance().LOGGER.info("Hello World!");
        System.out.println(JsonUtils.getInstance().toJson(AppConfig.getInstance()));
    }

}

