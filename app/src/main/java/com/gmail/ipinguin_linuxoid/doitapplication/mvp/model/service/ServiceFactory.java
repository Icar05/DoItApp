package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Фабрика запросов
 */
public class ServiceFactory {


    // из любого говна зделаем интерфейс  с методом запроса!
   public static <T> T createRetrofitService(final Class<T> clazz, final String baseUrl) {

       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(baseUrl)
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
               .addConverterFactory(GsonConverterFactory.create())
               .addConverterFactory(ScalarsConverterFactory.create())
               .build();



        return retrofit.create(clazz);
    }

}
