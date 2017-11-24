package com.fluidsoft.fluidsoft.tgconnect.model;

public class Event {
    private String imageEvent;
    private String dateEvent;
    private String locationEvent;
    private String timeEvent;
    public String join;

    public Event() {

    }


    public Event(String imageEvent, String dateEvent, String locationEvent, String timeEvent, String join) {
        this.imageEvent = imageEvent;
        this.dateEvent = dateEvent;
        this.locationEvent = locationEvent;
        this.timeEvent = timeEvent;
        this.join=join;

    }

    public String getImageEvent() {
        return imageEvent;
    }

    public void setImageEvent(String imageEvent) {
        this.imageEvent = imageEvent;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getLocationEvent() {
        return locationEvent;
    }

    public void setLocationEvent(String locationEvent) {
        this.locationEvent = locationEvent;
    }

    public String getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(String timeEvent) {
        this.timeEvent = timeEvent;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }
}