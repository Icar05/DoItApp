package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.api;


import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;


/**
 * линк на регистрацию
 */

public interface RegisterApi {

    String SERVICE_ENDPOINT = "http://api.doitserver.in.ua/";


    @POST("create")
    @Multipart
    Observable<Response<ResponseBody>> register(
            @Part("username") String username,
            @Part("email") String email,
            @Part("password") String password,
            @Part MultipartBody.Part file
    );

}
