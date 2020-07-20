package com.outfoxedstudios.superherocli.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Biography {

    @SerializedName("full-name")
    String       fullName;
    List<String> alterEgos;
    List<String> aliases;
    @SerializedName("place-of-birth")
    String       placeOfBirth;
    @SerializedName("first-appearance")
    String       firstAppearance;
    String       publisher;
    String       alignment;

    public String getFullName() {
        return fullName.equals("-") ? "N/A" : fullName;
    }

    public List<String> getAlterEgos() {
        return alterEgos;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth.equals("-") ? "N/A" : placeOfBirth;
    }

    public String getFirstAppearance() {
        return firstAppearance.equals("-") ? "N/A" : firstAppearance;
    }

    public String getPublisher() {
        return publisher.equals("-") ? "N/A" : publisher;
    }

    public String getAlignment() {
        return alignment.equals("-") ? "N/A" : alignment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("BIOGRAPHY\r\n");
        sb.append("  Full Name:         " + getFullName() + "\r\n");
//        sb.append("  Alter-Egos:      " + formatAlterEgosString()); // TODO: Handle alter-egos or remove
        sb.append("  Aliases:           " + formatAliasesString() + "\r\n");
        sb.append("  Place of Birth:    " + getPlaceOfBirth() + "\r\n");
        sb.append("  First Appearance:  " + getFirstAppearance() + "\r\n");
        sb.append("  Publisher:         " + getPublisher() + "\r\n");
        sb.append("  Alignment:         " + getAlignment());

        return sb.toString();
    }

    private String formatAliasesString() {
        if(aliases == null || aliases.isEmpty()) {
            return "N/A";
        }

        StringBuilder sb = new StringBuilder();

        for(String alias: getAliases()) {
            if(!sb.toString().isEmpty()) {
                sb.append(", ");
            }
            sb.append(alias);
        }

        return sb.toString();
    }
}
