package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import rx.Observable;

/**
 * получаем гиф из картинок
 */

public interface GetGifApi {

    String SERVICE_ENDPOINT = "http://api.doitserver.in.ua/";


    @GET("gif")
    Observable<Response<ResponseBody>> getGif(
            @HeaderMap Map<String, String> headers
    );

}
