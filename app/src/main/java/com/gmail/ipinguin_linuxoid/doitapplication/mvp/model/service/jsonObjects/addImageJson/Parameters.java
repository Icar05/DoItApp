package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson;

/**
 * Parameters
 */

public class Parameters {

    private String address;

    private String weather;

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
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

    @Override
    public String toString()
    {
        return "ClassPojo [address = "+address+", weather = "+weather+"]";
    }
}
