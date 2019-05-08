package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson;

/**
 * json в случае успешного запроса
 */

public class AddImageSuccessJson {

    private String smallImage;

    private Parameters parameters;

    private String bigImage;

    public String getSmallImage ()
    {
        return smallImage;
    }

    public void setSmallImage (String smallImage)
    {
        this.smallImage = smallImage;
    }

    public Parameters getParameters ()
    {
        return parameters;
    }

    public void setParameters (Parameters parameters)
    {
        this.parameters = parameters;
    }

    public String getBigImage ()
    {
        return bigImage;
    }

    public void setBigImage (String bigImage)
    {
        this.bigImage = bigImage;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [smallImage = "+smallImage+", parameters = "+parameters+", bigImage = "+bigImage+"]";
    }

}
