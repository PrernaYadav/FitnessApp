package com.fluidsoft.fluidsoft.tgconnect.model;

public class EventPrevious {

    private String imageEventPrevious;
    private String dateEventPrevious;
    private String locationEventPrevious;
    private String timeEventPrevious;
    public String joinPrevious;
    public EventPrevious() {
    }

    public EventPrevious(String imageEventPrevious, String dateEventPrevious, String locationEventPrevious, String timeEventPrevious) {
        this.imageEventPrevious = imageEventPrevious;
        this.dateEventPrevious = dateEventPrevious;
        this.locationEventPrevious = locationEventPrevious;
        this.timeEventPrevious = timeEventPrevious;
//        this.joinPrevious = joinPrevious;
    }

    public String getImageEventPrevious() {
        return imageEventPrevious;
    }

    public void setImageEventPrevious(String imageEventPrevious) {
        this.imageEventPrevious = imageEventPrevious;
    }

    public String getDateEventPrevious() {
        return dateEventPrevious;
    }

    public void setDateEventPrevious(String dateEventPrevious) {
        this.dateEventPrevious = dateEventPrevious;
    }

    public String getLocationEventPrevious() {
        return locationEventPrevious;
    }

    public void setLocationEventPrevious(String locationEventPrevious) {
        this.locationEventPrevious = locationEventPrevious;
    }

    public String getTimeEventPrevious() {
        return timeEventPrevious;
    }

    public void setTimeEventPrevious(String timeEventPrevious) {
        this.timeEventPrevious = timeEventPrevious;
    }
    public String getJoinPrevious() {
        return joinPrevious;
    }
    public void setJoinPrevious(String joinPrevious) {
        this.joinPrevious = joinPrevious;
    }
}
