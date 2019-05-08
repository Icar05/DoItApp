package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.signUpJson;


import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.jsonErrorChild;

/**
 * ошибка почты
 */

public class Email implements jsonErrorChild {

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
