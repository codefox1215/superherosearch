package com.outfoxedstudios.superherocli.model;

public class Work {

    String occupation;
    String base;

    public String getOccupation() {
        return occupation.equals("-") ? "N/A" : occupation;
    }

    public String getBase() {
        return base.equals("-") ? "N/A" : base;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("WORK\r\n");
        sb.append("  Occupation:  " + getOccupation() + "\r\n");
        sb.append("  Base:        " + getBase());

        return sb.toString();
    }
}
