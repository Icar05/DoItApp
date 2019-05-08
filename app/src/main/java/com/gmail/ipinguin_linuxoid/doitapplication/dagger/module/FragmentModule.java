package com.gmail.ipinguin_linuxoid.doitapplication.dagger.module;

import android.content.Context;

import com.gmail.ipinguin_linuxoid.doitapplication.dagger.scopes.ActivityScope;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.JsonErrorHelpParser;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.LoadImageHelper;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.MyLocationManager;

import javax.annotation.Nonnull;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль на уровне
 * фрагментов
 */
@ActivityScope
@Module
public class FragmentModule {



    @ActivityScope
    @Provides
    @Nonnull
    MyLocationManager provideMyLocationManager(Context context){
        return new MyLocationManager(context);
    }

    @ActivityScope
    @Provides
    @Nonnull
    LoadImageHelper provideLoadImageHelper(){
        return new LoadImageHelper();
    }


    @ActivityScope
    @Provides
    @Nonnull
    JsonErrorHelpParser provideJEHP(){
        return new JsonErrorHelpParser();
    }



}
