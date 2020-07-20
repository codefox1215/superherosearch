package com.outfoxedstudios.superherocli.model;

public class PowerStats {

    String intelligence;
    String strength;
    String speed;
    String durability;
    String power;
    String combat;

    public String getIntelligence() {
        return intelligence.equals("null") ? "?": intelligence;
    }

    public String getStrength() {
        return strength.equals("null") ? "?" : strength;
    }

    public String getSpeed() {
        return speed.equals("null") ? "?" : speed;
    }

    public String getDurability() {
        return durability.equals("null") ? "?" : durability;
    }

    public String getPower() {
        return power.equals("null") ? "?" : power;
    }

    public String getCombat() {
        return combat.equals("null") ? "?" : combat;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("POWER STATS\r\n");
        sb.append("  Intelligence:  " + getIntelligence() + "\r\n");
        sb.append("  Strength:      " + getStrength() + "\r\n");
        sb.append("  Speed:         " + getSpeed() + "\r\n");
        sb.append("  Durablity:     " + getDurability() + "\r\n");
        sb.append("  Power:         " + getPower() + "\r\n");
        sb.append("  Combat:        " + getCombat());

        return sb.toString();
    }
}
