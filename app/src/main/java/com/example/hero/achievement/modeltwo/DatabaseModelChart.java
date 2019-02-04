package com.example.hero.achievement.modeltwo;

public class DatabaseModelChart {

    private String dateAndHour;
    private int hour;

    public DatabaseModelChart(String dateAndHour, int hour) {
        this.dateAndHour = dateAndHour;
        this.hour = hour;
    }

    public String getDateAndHour() {
        return dateAndHour;
    }

    public void setDateAndHour(String dateAndHour) {
        this.dateAndHour = dateAndHour;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
