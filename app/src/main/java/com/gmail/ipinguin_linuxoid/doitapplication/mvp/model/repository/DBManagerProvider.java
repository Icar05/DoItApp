package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Хелпер для базы данных
 */
public class DBManagerProvider {


    // задаем и убираем бд
    private static DataBaseManager databaseManager;

    public static DataBaseManager getManager(){
        return databaseManager;
    }
    public static void setHelper(Context context){
        databaseManager = OpenHelperManager.getHelper(context, DataBaseManager.class);
    }

    public static void releaseHelper(){
        OpenHelperManager.releaseHelper();
        databaseManager = null;
    }
}
