package com.tuana9a.learn.java.servlet.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
    private static final Logger LOGGER = LogManager.getLogger(LogUtils.class);

    private LogUtils() {

    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
