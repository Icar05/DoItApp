package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.api;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * роут на добавление картинки
 */

public interface AddImageApi {

    String SERVICE_ENDPOINT = "http://api.doitserver.in.ua/";


    @POST("image")
    @Multipart
    Observable<Response<ResponseBody>> addImage(
            @Part MultipartBody.Part file,
            @Part("description") String description,
            @Part("hashtag") String hashtag,
            @Part("latitude") double latitude,
            @Part("longitude") double longitude,
            @HeaderMap Map<String, String> headers
    );


}
