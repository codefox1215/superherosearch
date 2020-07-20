package com.outfoxedstudios.superherocli.controller;

import com.google.gson.Gson;
import com.outfoxedstudios.superherocli.config.AppProperties;
import com.outfoxedstudios.superherocli.exception.APIRequestException;
import com.outfoxedstudios.superherocli.exception.APISearchException;
import com.outfoxedstudios.superherocli.logger.CLILogger;
import com.outfoxedstudios.superherocli.model.SearchResults;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HeroInfoController {

    private static String SEARCH_ENDPOINT = "search";

    private String uri;

    public HeroInfoController() {
        uri = AppProperties.getApiUri() + AppProperties.getApiKey() + "/";
    }

    public SearchResults search(String name) throws APISearchException {
        name = sanitizeSearchString(name);

        StringBuilder response = new StringBuilder();

        try {
            String urlStr = uri + SEARCH_ENDPOINT + "/" + name;
            CLILogger.debug("url => " + urlStr);
            URL url = new URL(urlStr);

            HttpsURLConnection httpsClient = (HttpsURLConnection) url.openConnection();
            httpsClient.setRequestMethod("GET");
            httpsClient.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = httpsClient.getResponseCode();
            if(responseCode != 200) {
                throw new APIRequestException("Error retrieving data from external API (Response code: " + responseCode + ")");
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(httpsClient.getInputStream()));

            String line;

            while((line = in.readLine()) != null) {
                CLILogger.debug(line);
                response.append(line);
            }

        } catch(Exception e) {
            throw new APISearchException(e.getMessage());
        }

        Gson gson = new Gson();

        SearchResults results = gson.fromJson(response.toString(), SearchResults.class);

        if(!results.getResponse().equals("success")) {
            throw new APISearchException(results.getError());
        }

        return results;
    }

    private String sanitizeSearchString(String str) {
        return str.trim();
    }
}
