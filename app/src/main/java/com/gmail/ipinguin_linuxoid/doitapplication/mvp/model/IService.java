package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model;

import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.ImageEntity;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.UserSettings;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

/**
 * Интерфейс который должен реализовать сервис
 * выполнябщий запросы по интернету
 */

public interface IService {



    //получение гиф
    public Observable<Response<ResponseBody>> getGif(String token);


    //получение картинок
    public Observable<Response<ResponseBody>> getImages(String token);


    //регистрация
    public Observable<Response<ResponseBody>> singIn(UserSettings settings);


    /*
      запрос на добавление картинки
     */
    public Observable<Response<ResponseBody>> addImage(ImageEntity imageEntity, String token);

    /*
     роут на login, на выходе респонс
     */
    public Observable<Response<ResponseBody>> login(String email,  String pass);



}
