package com.outfoxedstudios.superherocli.logger;

import com.outfoxedstudios.superherocli.config.AppProperties;

public class CLILogger {

    public static void error(String message) {
        LoggingLevel loggingLevel = AppProperties.getLoggingLevel();
        if(AppProperties.getLoggingLevel() == LoggingLevel.ERROR ||
                AppProperties.getLoggingLevel() == LoggingLevel.WARN ||
                AppProperties.getLoggingLevel() == LoggingLevel.INFO ||
                AppProperties.getLoggingLevel() == LoggingLevel.DEBUG) {
            printLogMessage("[ERROR] " + message);
        }
    }

    public static void warn(String message) {
        LoggingLevel loggingLevel = AppProperties.getLoggingLevel();
        if(AppProperties.getLoggingLevel() == LoggingLevel.WARN ||
                AppProperties.getLoggingLevel() == LoggingLevel.INFO ||
                AppProperties.getLoggingLevel() == LoggingLevel.DEBUG) {
            printLogMessage("[WARN] " + message);
        }
    }

    public static void info(String message) {
        LoggingLevel loggingLevel = AppProperties.getLoggingLevel();
        if(AppProperties.getLoggingLevel() == LoggingLevel.INFO ||
                AppProperties.getLoggingLevel() == LoggingLevel.DEBUG) {
            printLogMessage("[INFO] " + message);
        }
    }

    public static void debug(String message) {
        if(AppProperties.getLoggingLevel() == LoggingLevel.DEBUG) {
            printLogMessage("[DEBUG] " + message);
        }
    }

    public static void output(String message) {
        System.out.println(message);
    }

    private static void printLogMessage(String message) {
        System.out.println("\r\n");
        System.out.println(message);
        System.out.println("\r\n");
    }
}
