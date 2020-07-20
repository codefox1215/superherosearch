package com.outfoxedstudios.superherocli.model;

import java.util.List;

public class SearchResults {

    String     response;
    String     resultsFor;
    List<Hero> results;
    String     error;

    public String getResponse() {
        return response;
    }

    public String getResultsFor() {
        return resultsFor;
    }

    public List<Hero> getResults() {
        return results;
    }

    public String getError() {
        return error;
    }
}
