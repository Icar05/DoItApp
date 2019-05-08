package com.gmail.ipinguin_linuxoid.doitapplication.dagger.module;

import android.content.Context;

import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.IService;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.Interactor;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.DataBaseManager;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.PrefManager;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.NetworkService;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * От сюда будем провайдить завистимости
 */
@Module
@Singleton
public class AppModule {


    Context context;
    PrefManager prefManager;
    DataBaseManager dataBaseManager;

    public AppModule(Context context, PrefManager prefManager, DataBaseManager dataBaseManager) {
        this.context = context;
        this.prefManager = prefManager;
        this.dataBaseManager = dataBaseManager;
    }

    @Singleton
    @Provides
    @Nonnull
    Context provideContext(){
        return context;
    }



    @Singleton
    @Provides
    @Nonnull
    DataBaseManager provideDataBaseManager(){
        return dataBaseManager;
    }

    @Singleton
    @Provides
    @Nonnull
    PrefManager providePrefManager(){
        return prefManager;
    }



    @Singleton
    @Provides
    @Nonnull
    Interactor provideInteractor(IService iService, DataBaseManager dataBaseManager, PrefManager prefManager){
        return new Interactor(iService, prefManager, dataBaseManager);
    }


    @Singleton
    @Provides
    @Nonnull
    IService provideIService(){
        return new NetworkService();
    }

}
