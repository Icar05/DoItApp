package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson;

/**
 * часть проблем с добавлением
 */

public class Children {

    private Description description;

    private Hashtag hashtag;

    private Image image;

    private Longitude longitude;

    private Latitude latitude;


    public Description getDescription() {
        return description;
    }

    public Hashtag getHashtag() {
        return hashtag;
    }

    public Image getImage() {
        return image;
    }

    public Longitude getLongitude() {
        return longitude;
    }

    public Latitude getLatitude() {
        return latitude;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [description = "+description+", hashtag = "+hashtag+", image = "+image+", longitude = "+longitude+", latitude = "+latitude+"]";
    }
}
