package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.signUpJson;

/**
 * вложеный класс
 */

public class Children {


    private Username username;

    private Email email;

    private Avatar avatar;

    private Password password;

    public Username getUsername ()
    {
        return username;
    }

    public Email getEmail ()
    {
        return email;
    }

    public Avatar getAvatar ()
    {
        return avatar;
    }

    public Password getPassword ()
    {
        return password;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [username = "+username+", email = "+email+", avatar = "+avatar+", password = "+password+"]";
    }
}
