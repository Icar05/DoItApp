package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.api;


import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Роут на логин
 */

public interface LoginApi {

    String SERVICE_ENDPOINT = "http://api.doitserver.in.ua/";


    @POST("login")
    @Multipart
    Observable<Response<ResponseBody>> login(
            @Part("email") String email,
            @Part("password") String password
    );

}


