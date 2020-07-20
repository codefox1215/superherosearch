package com.outfoxedstudios.superherocli.controller;

import com.outfoxedstudios.superherocli.config.AppProperties;
import com.outfoxedstudios.superherocli.exception.APISearchException;
import com.outfoxedstudios.superherocli.logger.CLILogger;
import com.outfoxedstudios.superherocli.model.Hero;
import com.outfoxedstudios.superherocli.model.SearchResults;

import java.util.Scanner;

public class CLIController {

    private HeroInfoController heroInfoController = new HeroInfoController();
    private Scanner            inputScanner       = new Scanner(System.in);
    private String             currentInput;
    private SearchResults      currentResults;


    public void run() {
        displayWelcome();

        boolean done = false;

        while(!done) {
            boolean searchError;
            do {
                searchError = false;
                promptSearchQuery();
                try {
                    currentResults = heroInfoController.search(currentInput);
                } catch (APISearchException e) {
                    CLILogger.error(e.getMessage());
                    searchError = true;
                }
            } while (searchError);

            int selectedResultIndex = 0;
            if(currentResults.getResults().size() > 1) {
                selectedResultIndex = displayResultsOptions();
            }
            displayHeroInformation(currentResults.getResults().get(selectedResultIndex));

            done = true; // TODO: setup loop for multiple searches
        }

    }

    private void displayWelcome() {
        CLILogger.output("Welcome to HeroSearch CLI");
        CLILogger.output("");
    }

    private void promptSearchQuery() {
        boolean valid;
        int minSearchTermLength = AppProperties.getMinSearchTermLength();
        do {
            valid = true;
            CLILogger.output("What hero to you wish to search?");
            currentInput = inputScanner.next().trim();
            if(currentInput.length() < minSearchTermLength) {
                CLILogger.warn("Search term must be greater than " + minSearchTermLength + " characters");
            }
        } while (!valid);
    }

    private int displayResultsOptions() {
        int index = 1;
        for(Hero hero: currentResults.getResults()) {
            CLILogger.output("[" + index + "] " + hero.getName() + "(" + hero.getBiography().getFullName() + ")");
            index++;
        }
        CLILogger.output("");
        CLILogger.output("Who do you want info on?");
        String inputVal = inputScanner.next();

        return Integer.parseInt(inputVal) - 1;
    }

    private void displayHeroInformation(Hero hero) {
        CLILogger.output(hero.toString());
    }

}
