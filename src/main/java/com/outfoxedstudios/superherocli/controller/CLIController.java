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

        boolean done;

        main_loop:
        do {
            boolean searchError;

            // reset values
            currentInput = "";
            currentResults = null;

            do {
                // reset error flag
                searchError = false;

                // display search prompt
                promptSearchQuery();

                // search api for results
                try {
                    currentResults = heroInfoController.search(currentInput);
                } catch (APISearchException e) {
                    CLILogger.error(e.getMessage());
                    searchError = true;
                }
            } while (searchError);

            boolean resultsDone = false;

            results_loop:
            do {

                int selectedResultIndex = 0;
                if (currentResults.getResults().size() > 0) {
                    selectedResultIndex = displayResultsOptions();
                    if (isGoBackSelection(selectedResultIndex)) {
                        done = false;
                        continue main_loop;
                    }
                    if (isExitSelection(selectedResultIndex)) {
                        exitCLI();
                    }
                }
                displayHeroInformation(currentResults.getResults().get(selectedResultIndex));

                int nextSelection = displayNewSearchOption();

                switch (nextSelection) {
                    case 1:
                        resultsDone = false;
                        continue results_loop;
                    case 2:
                        done = false;
                        continue main_loop;
                    case 3:
                        exitCLI();
                        break;
                    default:
                }
            } while(!resultsDone);

            done = true; // TODO: setup loop for multiple searches
        } while(!done);

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

    private void displayHeroInformation(Hero hero) {
        CLILogger.output(hero.toString());
    }

    private int displayNewSearchOption() {
        do {
            CLILogger.output("Would you like to search again?");
            CLILogger.output("[1] Go back to search results");
            CLILogger.output("[2] New Search]");
            CLILogger.output("[3] Exit]");
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

}
