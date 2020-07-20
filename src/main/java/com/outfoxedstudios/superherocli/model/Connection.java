package com.outfoxedstudios.superherocli.model;

import com.google.gson.annotations.SerializedName;

public class Connection {

    @SerializedName("group-affiliation")
    String groupAffiliation;
    String relatives;

    public String getGroupAffiliation() {
        return groupAffiliation.equals("-") ? "N/A" : groupAffiliation;
    }

    public String getRelatives() {
        return relatives.equals("-") ? "N/A" : relatives;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("CONNECTION\r\n");
        sb.append("  Group Affiliation:  " + getGroupAffiliation() + "\r\n");
        sb.append("  Relatives:          " + getRelatives());

        return sb.toString();
    }
}
