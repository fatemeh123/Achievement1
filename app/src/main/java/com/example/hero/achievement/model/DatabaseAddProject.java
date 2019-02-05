package com.example.hero.achievement.model;

public class DatabaseAddProject {

    private String subjectName;
    private int priority;
    private int daysPerWeek;
    private int hoursPerSession;

    public DatabaseAddProject(String subjectName, int priority, int daysPerWeek, int hoursPerSession) {
        this.subjectName = subjectName;
        this.priority = priority;
        this.daysPerWeek = daysPerWeek;
        this.hoursPerSession = hoursPerSession;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(int daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public int getHoursPerSession() {
        return hoursPerSession;
    }

    public void setHoursPerSession(int hoursPerSession) {
        this.hoursPerSession = hoursPerSession;
    }
}
