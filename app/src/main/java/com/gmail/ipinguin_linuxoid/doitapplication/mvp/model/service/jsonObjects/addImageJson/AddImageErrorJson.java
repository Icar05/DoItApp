package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson;



/**
 * Если ошибка при добавлении картинки
 */

public class AddImageErrorJson {

    private Children children;

    public Children getChildren ()
    {
        return children;
    }

    public void setChildren (Children children)
    {
        this.children = children;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [children = "+children+"]";
    }

}
