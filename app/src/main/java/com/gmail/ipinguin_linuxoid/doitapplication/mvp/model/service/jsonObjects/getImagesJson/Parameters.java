package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.getImagesJson;

/**
 * Params
 */

public class Parameters {


    private String address;

    private String weather;

    private double longitude;

    private double latitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeather ()
    {
        return weather;
    }

    public void setWeather (String weather)
    {
        this.weather = weather;
    }

    public double getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (double longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [weather = "+weather+", longitude = "+longitude+", latitude = "+latitude+"]";
    }

}
