package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository;

/**
 * Обьект хранящий пользовательские настройки
 */

public class UserSettings {

   private String username;
   private String pass;
   private String email;
   private String url;
   private String token;

    public UserSettings(String username,  String email, String pass, String url) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
