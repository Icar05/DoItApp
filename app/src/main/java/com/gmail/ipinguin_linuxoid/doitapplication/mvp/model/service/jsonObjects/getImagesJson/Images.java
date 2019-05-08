package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.getImagesJson;

/**
 * массив картинок
 */

public class Images {
    private String id;

    private String smallImagePath;

    private String created;

    private String description;

    private String hashtag;

    private Parameters parameters;

    private String bigImagePath;



    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getSmallImagePath ()
    {
        return smallImagePath;
    }

    public void setSmallImagePath (String smallImagePath)
    {
        this.smallImagePath = smallImagePath;
    }

    public String getCreated ()
    {
        return created;
    }

    public void setCreated (String created)
    {
        this.created = created;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getHashtag ()
    {
        return hashtag;
    }

    public void setHashtag (String hashtag)
    {
        this.hashtag = hashtag;
    }

    public Parameters getParameters ()
    {
        return parameters;
    }

    public void setParameters (Parameters parameters)
    {
        this.parameters = parameters;
    }

    public String getBigImagePath ()
    {
        return bigImagePath;
    }

    public void setBigImagePath (String bigImagePath)
    {
        this.bigImagePath = bigImagePath;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", smallImagePath = "+smallImagePath+", created = "+created+", description = "+description+", hashtag = "+hashtag+", parameters = "+parameters+", bigImagePath = "+bigImagePath+"]";
    }
}
