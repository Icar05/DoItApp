package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Хелпер для таблиц бд
 */



public class DataBaseManager extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DataBaseManager.class.getSimpleName();

    //имя файла базы данных который будет храниться в /data/data/APPNAME/DATABASE_NAME.db
    private static final String DATABASE_NAME ="doItapp.db";

    //с каждым увеличением версии, при нахождении в устройстве БД с предыдущей версией будет выполнен метод onUpgrade();
    private static final int DATABASE_VERSION = 1;



    //ссылки на DAO соответсвующие сущностям, хранимым в БД
    private ImageDao imageDao = null;
//    // сюда добавлюем дао


    public DataBaseManager(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Выполняется, когда файл с БД не найден на устройстве
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
        try
        {
            TableUtils.createTable(connectionSource, ImageEntity.class);
//            // сюда добавлюем таблицу

        }
        catch (SQLException e){
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }



    //Выполняется, когда БД имеет версию отличную от текущей
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer, int newVer){
        try{
            //Так делают ленивые, гораздо предпочтительнее не удаляя БД аккуратно вносить изменения
            TableUtils.dropTable(connectionSource, ImageEntity.class, true);
            // сюда добавлюем таблицу
            onCreate(db, connectionSource);
        }
        catch (SQLException e){
            Log.e(TAG,"error upgrading db "+DATABASE_NAME+"from ver "+oldVer);
            throw new RuntimeException(e);
        }
    }

    //синглтон для ImageDao
    public ImageDao getImageDao() throws SQLException{
        if(imageDao == null){
            imageDao = new ImageDao(getConnectionSource(), ImageEntity.class);
        }
        return imageDao;
    }


    //  создаем синглтон для дао



    //выполняется при закрытии приложения
    @Override
    public void close(){
        super.close();
        imageDao = null;
        // закрываем дао
    }

}


