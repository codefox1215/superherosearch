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
        return fullName;
    }

    public List<String> getAlterEgos() {
        return alterEgos;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAlignment() {
        return alignment;
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
