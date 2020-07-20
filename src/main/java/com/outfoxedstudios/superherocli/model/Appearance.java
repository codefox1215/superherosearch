package com.outfoxedstudios.superherocli.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Appearance {

    String       gender;
    String       race;
    List<String> height;
    List<String> weight;
    @SerializedName("eye-color")
    String       eyeColor;
    @SerializedName("hair-color")
    String       hairColor;

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public String getHeight() {
        return height.get(0) + " / " + height.get(1);
    }

    public String getWeight() {
        return weight.get(0) + " / " + weight.get(1) ;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("APPEARANCE\r\n");
        sb.append("  Gender:      " + getGender() + "\r\n");
        sb.append("  Race:        " + getRace() + "\r\n");
        sb.append("  Height:      " + getHeight()+ "\r\n");
        sb.append("  Weight:      " + getWeight() + "\r\n");
        sb.append("  Eye Color:   " + getEyeColor() + "\r\n");
        sb.append("  Hair Color:  " + getHairColor());

        return sb.toString();
    }
}
