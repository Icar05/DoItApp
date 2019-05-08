package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson;


import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.jsonErrorChild;

/**
 * описание
 */

public class Description implements jsonErrorChild {

    private String[] errors;

    public String[] getErrors ()
    {
        return errors;
    }

    public void setErrors (String[] errors)
    {
        this.errors = errors;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [errors = "+errors+"]";
    }

    @Override
    public String getError() {
        return  errors == null ? null : errors[0];
    }
}
