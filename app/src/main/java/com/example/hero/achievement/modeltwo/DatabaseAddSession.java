package com.example.hero.achievement.modeltwo;

import android.util.Log;

/***
 * Database for adding sessions
 */
public class DatabaseAddSession {

    private String subjectName;
    private int satisfaction;
    private int hour;
    private int quality;
    private String date;

    public DatabaseAddSession(String subjectName , int satisfaction, int hour, int quality , String date) {
        this.subjectName = subjectName;
        this.satisfaction = satisfaction;
        this.hour = hour;
        this.quality = quality;
        this.date=date;
        Log.d("star","fatemeh is logging. satisfaction: "+satisfaction + "subjectnName:" + subjectName + "quality" + quality+
        "date: " + date);

    }

    public void setSubjectName(String subjectame) {
        this.subjectName = subjectame;
    }

    public String getSubjectName(){ return subjectName; }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setSdate(String date) { this.date = date; }

    public String getdate(){ return date; }
}
