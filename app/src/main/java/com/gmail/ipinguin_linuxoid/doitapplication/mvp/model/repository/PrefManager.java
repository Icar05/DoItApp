package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Менеджер статики
 */

public class PrefManager {

    private final String LOG = "mypreference";

    private String TAG = PrefManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "androidhive_gcm";


    // All Shared Preferences Keys
    private  String KEY_AVA_URL = "key_ava_url";

    // All Shared Preferences Keys
    private  String KEY_USERNAME = "key_username";

    // All Shared Preferences Keys
    private  String KEY_PASS = "key_pass";

    // All Shared Preferences Keys
    private  String KEY_EMAIL = "key_email";

    private  String KEY_TOKEN = "key_token";


    // Constructor
    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    public void setUserSettings(UserSettings settings){

        editor.putString(KEY_USERNAME, settings.getUsername());
        editor.putString(KEY_PASS, settings.getPass());
        editor.putString(KEY_EMAIL, settings.getEmail());
        editor.putString(KEY_AVA_URL, settings.getUrl());
        editor.putString(KEY_TOKEN, settings.getToken());
        editor.commit();
    }


    public UserSettings getUserSettings(){
        String username  = pref.getString(KEY_USERNAME, "");
        String email = pref.getString(KEY_EMAIL, "");
        String pass = pref.getString(KEY_PASS, "");
        String url = pref.getString(KEY_AVA_URL, "");
        String token = pref.getString(KEY_TOKEN, "");

        UserSettings settings = new UserSettings(username, email, pass, url );
        settings.setToken(token);

        return settings;

    }




}