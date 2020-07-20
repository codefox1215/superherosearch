package com.outfoxedstudios.superherocli.model;

public class Hero {

    int        id;
    String     name;
    PowerStats powerstats;
    Biography  biography;
    Appearance appearance;
    Work       work;
    Connection connections;
    Image      image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PowerStats getPowerstats() {
        return powerstats;
    }

    public Biography getBiography() {
        return biography;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public Work getWork() {
        return work;
    }

    public Connection getConnections() {
        return connections;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("NAME:  " + name + "\r\n");
        sb.append("\r\n");
        if(powerstats == null) {
            sb.append("powerstats => null");
        } else {
            sb.append(powerstats.toString());
        }
        sb.append("\r\n");
        sb.append("\r\n");
        if(biography == null) {
            sb.append("biography => null");
        } else {
            sb.append(biography.toString());
        }
        sb.append("\r\n");
        sb.append("\r\n");
        if(appearance == null) {
            sb.append("appearacne => null");
        } else {
            sb.append(appearance.toString());
        }
        sb.append("\r\n");
        sb.append("\r\n");
        if(work == null) {
            sb.append("work => null");
        } else {
            sb.append(work.toString());
        }
        sb.append("\r\n");
        sb.append("\r\n");
        if(connections == null) {
            sb.append("connection => null");
        } else {
            sb.append(connections.toString());
        }

        return sb.toString();
    }
}
