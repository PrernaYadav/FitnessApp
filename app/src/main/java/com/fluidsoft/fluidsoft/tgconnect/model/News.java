package com.fluidsoft.fluidsoft.tgconnect.model;

public class News  {

    private String imageUrl;
    private String title;
    private String description;
    private String url;

    public News() {

    }

    public News(String imageUrl, String title, String description, String url) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
