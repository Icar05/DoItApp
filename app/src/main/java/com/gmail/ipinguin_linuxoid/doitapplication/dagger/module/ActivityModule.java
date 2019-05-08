package com.gmail.ipinguin_linuxoid.doitapplication.dagger.module;

import android.app.Activity;

import com.gmail.ipinguin_linuxoid.doitapplication.MainActivity;
import com.gmail.ipinguin_linuxoid.doitapplication.dagger.scopes.ActivityScope;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.FragmentManagerWrapper;

import javax.annotation.Nonnull;

import dagger.Module;
import dagger.Provides;

/**
 * Модуь уровря активности
 */
@Module
@ActivityScope
public class ActivityModule {



    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }



    @ActivityScope
    @Provides
    @Nonnull
    FragmentManagerWrapper provideFMW(){

        return new FragmentManagerWrapper(
                ((MainActivity) this.activity).
                        getSupportFragmentManager());
    }

}
