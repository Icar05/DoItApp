package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import rx.Observable;

/**
 * Запрос на получение картинок
 */

public interface GetImagesApi {

    String SERVICE_ENDPOINT = "http://api.doitserver.in.ua/";


    @GET("all")
    Observable<Response<ResponseBody>> getImages(
            @HeaderMap Map<String, String> headers
    );

}
