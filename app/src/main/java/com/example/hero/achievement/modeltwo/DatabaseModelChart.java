package com.example.hero.achievement.modeltwo;

public class DatabaseModelChart {

    private String date;
    private int duration;

    public DatabaseModelChart(String dateAndHour, int hour) {
        this.date = dateAndHour;
        this.duration = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
