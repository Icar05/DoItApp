package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.signUpJson;

/**
 * Если ошибка при регистрации
 */

public class RegistrationErrorJson {


    private Children children;

    public Children getChildren ()
    {
        return children;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [children = "+children+"]";
    }
}