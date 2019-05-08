package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model;

import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.DataBaseManager;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.ImageEntity;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.PrefManager;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.UserSettings;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.getImagesJson.Images;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

/**
 * интерактор управляющий данными верхнего уровня
 * управляет интернетом и хранимыми данными
 */

public class Interactor {


    IService networkService;
    DataBaseManager dataBaseManager;
    PrefManager prefManager;

    final String LOG = "interactor";



    @Inject
    public Interactor(IService iService, PrefManager prefManager, DataBaseManager dataBaseManager) {
        this.networkService = iService;
        this.prefManager = prefManager;
        this.dataBaseManager  = dataBaseManager;
    }

    //получаем токен пользователя
    private String getToken(){
        return prefManager.getUserSettings().getToken();
    }

    //получаем гиф с сервера
    public Observable<Response<ResponseBody>> getGif(){
        return networkService.getGif(getToken());
    }

    //получаем все картинки с сервера
    public Observable<Response<ResponseBody>> getAllImages(){
        return networkService.getImages(getToken());
    }

    //пересохраняем картинки
    public void reSaveImages(Images[] images) throws SQLException{
        dataBaseManager.getImageDao().reSaveImages(images);
    }

    //достаем из кеша картинки
    public List<ImageEntity> getImagesFromCash()throws SQLException{
        return dataBaseManager.getImageDao().getAllImages();
    }

    //sing in
    public Observable<Response<ResponseBody>> singIn(UserSettings settings){
        return networkService.singIn(settings);
    }

    //сохраняем данные про пользователья
    public void saveUserSettings(UserSettings settings){
        prefManager.setUserSettings(settings);
    }


    //логинимся
    public Observable<Response<ResponseBody>> logIn(String email, String password){
        return networkService.login(email,password);
    }


    //send image on server
    public Observable<Response<ResponseBody>> sendImage(ImageEntity entity){
        return networkService.addImage(entity, getToken());
    }


    //сохраянем картинку
    public void cashImage(ImageEntity entity)throws SQLException{
        dataBaseManager.getImageDao().create(entity);
    }

    //возвращаем настройки пользователей
    public UserSettings getUserSettings(){
        return prefManager.getUserSettings();
    }

}
