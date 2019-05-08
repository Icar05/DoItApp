package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.getGIfJson;

/**
 * json for gif
 */

public class GifJson
{
    private String gif;

    public String getGif ()
    {
        return gif;
    }

    public void setGif (String gif)
    {
        this.gif = gif;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [gif = "+gif+"]";
    }
}
