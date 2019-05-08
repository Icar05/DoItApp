package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Сущность картиник для бд
 */


@DatabaseTable(tableName = "image_entity")
public class ImageEntity {


    public final static String IMAGE_URL = "img_url";
    public final static String IMAGE_DESC = "img_desc";
    public final static String IMAGE_HASHTAG = "img_hashtag";
    public final static String IMAGE_LATITUDE = "img_latitude";
    public final static String IMAGE_LONGITUDE = "img_longitude";
    public final static String IMAGE_ADDRESS = "img_address";
    public final static String IMAGE_WEATHER = "img_weather";




    @DatabaseField(generatedId = true)
    private int Id;


    @DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = IMAGE_URL)
    private String image_url;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = IMAGE_DESC)
    private String description;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = IMAGE_HASHTAG)
    private String hashtag;

    @DatabaseField(canBeNull = false, dataType = DataType.DOUBLE, columnName = IMAGE_LATITUDE)
    private double latitude;

    @DatabaseField(canBeNull = false, dataType = DataType.DOUBLE, columnName = IMAGE_LONGITUDE)
    private double longitude;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = IMAGE_ADDRESS)
    private String address;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = IMAGE_WEATHER)
    private String weather;

    public ImageEntity() {

    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}