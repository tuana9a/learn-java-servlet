package com.tuana9a.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
    private static final LogUtils instance = new LogUtils();

    public final Logger LOGGER = LogManager.getLogger(LogUtils.class);

    private LogUtils() {

    }

    public static LogUtils getInstance() {
        return instance;
    }

}
