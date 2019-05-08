package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.signUpJson;

/**
 * успешный запрос на регистрацию
 */

public class RegistrationSuccessJson {

    private String token;

    private String creation_time;

    private String avatar;



    public String getToken () {
        return token;
    }

    public String getCreation_time () {
        return creation_time;
    }

    public String getAvatar () {
        return avatar;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [token = "+token+", creation_time = "+creation_time+", avatar = "+avatar+"]";
    }
}
