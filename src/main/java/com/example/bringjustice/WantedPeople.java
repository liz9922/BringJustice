package com.example.bringjustice;

public class WantedPeople {
    private String description;
    private String imageURL;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Beskrivning: " + description
                + "\nNamn: " + title
                + "\nBildURL: " + imageURL;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
