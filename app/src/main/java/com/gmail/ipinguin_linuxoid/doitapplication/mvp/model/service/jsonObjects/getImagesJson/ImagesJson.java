package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.getImagesJson;

/**
 * обьект картинок
 */

public class ImagesJson {

    private Images[] images;

    public Images[] getImages ()
    {
        return images;
    }

    public void setImages (Images[] images)
    {
        this.images = images;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [images = "+images+"]";
    }

}
