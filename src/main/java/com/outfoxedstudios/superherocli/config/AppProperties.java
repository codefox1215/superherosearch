package com.outfoxedstudios.superherocli.config;

import com.outfoxedstudios.superherocli.logger.LoggingLevel;

public class AppProperties {

    private final static LoggingLevel LOGGING_LEVEL =          LoggingLevel.DEBUG;
    private final static String       API_URI =                "https://superheroapi.com/api/";
    private final static String       API_KEY =                "10111121773977642";
    private final static int          MIN_SEARCH_TERM_LENGTH = 2;


    public static LoggingLevel getLoggingLevel() {
        return LOGGING_LEVEL;
    }

    public static String getApiUri() {
        return API_URI;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static int getMinSearchTermLength() {
        return MIN_SEARCH_TERM_LENGTH;
    }
}
