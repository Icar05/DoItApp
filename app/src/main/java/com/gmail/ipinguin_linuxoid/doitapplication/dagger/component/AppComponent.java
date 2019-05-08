package com.gmail.ipinguin_linuxoid.doitapplication.dagger.component;

import android.content.Context;

import com.gmail.ipinguin_linuxoid.doitapplication.dagger.module.AppModule;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.Interactor;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank.AddImagePresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank.ImagesListPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank.LoginPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank.SignUpPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Компонент верхнего уровня
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {


    Interactor getInteractor();
    Context getContext();

    void inject(AddImagePresenter presenter);
    void inject(ImagesListPresenter presenter);
    void inject(LoginPresenter presenter);
    void inject(SignUpPresenter presenter);
}
