package com.fluidsoft.fluidsoft.tgconnect.model;

public class Athlete {

    private String athletePhoto,athleteName,athleteLike;

    public Athlete() {

    }

    public Athlete(String athletePhoto, String athleteName, String athleteLike) {
        this.athletePhoto = athletePhoto;
        this.athleteName = athleteName;
        this.athleteLike = athleteLike;
    }

    public String getAthletePhoto() {
        return athletePhoto;
    }

    public void setAthletePhoto(String athletePhoto) {
        this.athletePhoto = athletePhoto;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getAthleteLike() {
        return athleteLike;
    }

    public void setAthleteLike(String athleteLike) {
        this.athleteLike = athleteLike;
    }
}
