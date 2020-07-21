package com.outfoxedstudios.superherocli.controller;

import com.outfoxedstudios.superherocli.config.AppProperties;
import com.outfoxedstudios.superherocli.exception.APISearchException;
import com.outfoxedstudios.superherocli.exception.NoResultsException;
import com.outfoxedstudios.superherocli.logger.CLILogger;
import com.outfoxedstudios.superherocli.model.Hero;
import com.outfoxedstudios.superherocli.model.SearchResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLIController {

    private HeroInfoController heroInfoController = new HeroInfoController();
    private Scanner            inputScanner       = new Scanner(System.in);
    private CLIState           currentState;
    private SearchResults      currentResults;
    private Hero               selectedHero;
    private int                minSearchTermLength;


    public void run() {
        // initialize CLI state values
        init();

        displayWelcome();

        while(true){
            switch(currentState) {
                case QUERY_SEARCH_TERM:
                    // ensure search results are cleared before new search query
                    clearSearchValues();
                    querySearchTerm();
                    break;
                case RESULTS_SELECTION:
                    showResultsSelection();
                    break;
                case SHOW_RESULTS:
                    showResults();
                    break;
                case EXIT_CLI:
                    exitCLI();
                    break;
                default:
            }

        }

    }

    private void init() {
        setState(CLIState.QUERY_SEARCH_TERM);
        selectedHero = null;
        minSearchTermLength = AppProperties.getMinSearchTermLength();
    }

    private void querySearchTerm() {
        // display search prompt and retrieve
        String searchTerm = promptSearchQuery();

        boolean success = performSearch(searchTerm);

        if(success) {
            setState(CLIState.RESULTS_SELECTION);
        }
    }

    private void clearSearchValues() {
        currentResults = null;
        selectedHero = null;
    }

    private void showResultsSelection() {
        int selectedResultIndex = displayResultsOptions();

        if (isGoBackSelection(selectedResultIndex)) {
            setState(CLIState.QUERY_SEARCH_TERM);
        } else if (isExitSelection(selectedResultIndex)) {
            setState(CLIState.EXIT_CLI);
        } else {
            selectedHero = currentResults.getResults().get(selectedResultIndex);
            setState(CLIState.SHOW_RESULTS);
        }
    }

    private void  showResults() {
        displaySelectedHeroInformation();

        int nextSelection = displayNewSearchOption();

        switch (nextSelection) {
            case 1:
                setState(CLIState.RESULTS_SELECTION);
                break;
            case 2:
                setState(CLIState.QUERY_SEARCH_TERM);
                break;
            case 3:
                setState(CLIState.EXIT_CLI);
                break;
            default:
        }
    }

    private boolean performSearch(String searchTerm) {

        try {
            currentResults = heroInfoController.search(searchTerm);
        } catch(APISearchException e) {
            CLILogger.error(e.getMessage());
            CLILogger.output("Cannot connect to API.");
            CLILogger.output("Check your internet connection and try again.");
            exitCLI();
        } catch(NoResultsException e) {
            CLILogger.warn(e. getMessage());
            return false;
        }

        return true;
    }

    private void displayWelcome() {
        CLILogger.output("Welcome to HeroSearch CLI");
        CLILogger.output("");
    }

    private String promptSearchQuery() {
        boolean valid;
        String searchInput = "";

        do {
            valid = true;
            CLILogger.output("What hero to you wish to search?");
            searchInput = inputScanner.next().trim();
            if(searchInput.length() < minSearchTermLength) {
                CLILogger.warn("Search term must be greater than " + minSearchTermLength + " characters");
                valid = false;
            }
        } while (!valid);

        return searchInput;
    }

    private int displayResultsOptions() {
        boolean valid;
        int index;

        do {
            valid = true;
            index = 1;
            for (Hero hero : currentResults.getResults()) {
                CLILogger.output("[" + index + "] " + hero.getName() + "(" + hero.getBiography().getFullName() + ")");
                index++;
            }

            CLILogger.output("[" + index + "] Go back");
            index++;

            CLILogger.output("[" + index + "] Exit");

            CLILogger.output("");
            CLILogger.output("Who do you want info on?");
            String inputVal = inputScanner.next();
            index = Integer.parseInt(inputVal);

            // Check if selection is in range of selections List<Hero> size + 2 for "Go Back"/"Exit"
            if(index < 1 || index > currentResults.getResults().size() + 2) {
                valid = false;
                CLILogger.warn("Invalid selection");
            }

        } while(!valid);

        return index - 1;
    }

    private void displaySelectedHeroInformation() {
        CLILogger.output(selectedHero.toString());
    }

    private int displayNewSearchOption() {
        do {
            CLILogger.output("Would you like to search again?");
            CLILogger.output("[1] Go back to search results");
            CLILogger.output("[2] New Search");
            CLILogger.output("[3] Exit");
            String input = inputScanner.next().trim();

            int selection = Integer.parseInt(input);

            if (selection > 0 && selection <= 3) {
                return selection;
            }
        } while(true);
    }

    private boolean isGoBackSelection(int selectionIndex) {
        return (selectionIndex == currentResults.getResults().size());
    }

    private boolean isExitSelection(int selectionIndex) {
        return (selectionIndex == (currentResults.getResults().size() + 1));
    }

    private void exitCLI() {
        CLILogger.output("Exiting...");
        System.exit(0);
    }

    private void setState(CLIState state) {
        currentState = state;
    }

}
