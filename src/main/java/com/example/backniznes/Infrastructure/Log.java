package com.example.backniznes.Infrastructure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    public static void error(String className, String message) {
        LogManager.getLogger(className).error(message);
    }

    public static void info(String className, String message) {
        LogManager.getLogger(className).info(message);
    }

    public static void debug(String className, String message) {
        LogManager.getLogger(className).debug(message);
    }

    public static void fatal(String className, String message) {
        LogManager.getLogger(className).fatal(message);
    }
    public static void trace(String className, String message) {
        LogManager.getLogger(className).trace(message);
    }

    public static void warn(String className, String message) {
        LogManager.getLogger(className).warn(message);
    }
}
