package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service;


import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.IService;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.ImageEntity;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.UserSettings;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.api.AddImageApi;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.api.GetGifApi;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.api.GetImagesApi;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.api.LoginApi;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.api.RegisterApi;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * класс отвечающий за запросы к серверу
 */

public class NetworkService implements IService {



    /*
    роут на получение картинок
   */
    public Observable<Response<ResponseBody>> getGif(String token){

        //создаем сервис
        GetGifApi api = ServiceFactory.createRetrofitService(GetGifApi.class, GetGifApi.SERVICE_ENDPOINT);


        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);


        return  api.getGif(headers)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /*
      роут на получение картинок
     */
    public Observable<Response<ResponseBody>> getImages(String token){

        //создаем сервис
        GetImagesApi api = ServiceFactory.createRetrofitService(GetImagesApi.class, GetImagesApi.SERVICE_ENDPOINT);


        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);


        return  api.getImages(headers)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /*
     роут на регистрацию, на выходе респонс
     */
    public Observable<Response<ResponseBody>> singIn(UserSettings settings){


            File ava = new File(settings.getUrl());

            //создаем сервис
            RegisterApi api = ServiceFactory.createRetrofitService(RegisterApi.class, RegisterApi.SERVICE_ENDPOINT);


            //тело запроса
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), ava);

            // MultipartBody.Part используется, чтобы передать имя файла
            MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", ava.getName(), requestFile);


           return  api.register(settings.getUsername(),
                   settings.getEmail(),
                   settings.getPass()
                   , body)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());


    }


    /*
      запрос на добавление картинки
     */
    public Observable<Response<ResponseBody>> addImage(ImageEntity imageEntity, String token){

        File ava = new File(imageEntity.getImage_url());

        //тело запроса
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), ava);

        // MultipartBody.Part используется, чтобы передать имя файла
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", ava.getName(), requestFile);

        //создаем сервис
        AddImageApi api = ServiceFactory.createRetrofitService(AddImageApi.class, AddImageApi.SERVICE_ENDPOINT);


        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);



        return  api.addImage(body,
                 imageEntity.getDescription(),
                 imageEntity.getHashtag(),
                 imageEntity.getLatitude(),
                 imageEntity.getLongitude(),
                 headers
                )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /*
     роут на login, на выходе респонс
     */
    public Observable<Response<ResponseBody>> login(String email,  String pass){

        LoginApi api = ServiceFactory.createRetrofitService(LoginApi.class, LoginApi.SERVICE_ENDPOINT);


        return  api.login(email, pass)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());


    }



}
