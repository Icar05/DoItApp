package com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.ApplicationManager;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.Interactor;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.UserSettings;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.signUpJson.RegistrationErrorJson;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.signUpJson.RegistrationSuccessJson;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank.SignUpView;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;


@InjectViewState
public class SignUpPresenter extends MvpPresenter<SignUpView> {


    private static final String TAG = "registrationPresenter";

    @Inject
    Interactor interactor;

    public SignUpPresenter() {
        ApplicationManager.getInstance().getAppComponent().inject(this);
    }



    /*
     запрос на регистрацию
     */
    public void registerUser(UserSettings settings){


        getViewState().showProgress();

        interactor.singIn(settings).subscribe(response -> {

           //прячем прогресс бар
           getViewState().hideProgress();


           Gson gson = new Gson();
           try {

           // если запрос успешный - просто приветстуем и выходим
                if(response.isSuccessful()){

                    RegistrationSuccessJson obj = gson.fromJson(response.body().string(),RegistrationSuccessJson.class);

                    settings.setUrl(obj.getAvatar());
                    settings.setToken(obj.getToken());
                    interactor.saveUserSettings(settings);

                    getViewState().onSuccessResponse();

                }else{ //иначе получаем джейсон обьект ошибок и отдаетм клиенту на обработку

                    RegistrationErrorJson obj = gson.fromJson(response.errorBody().string(),RegistrationErrorJson.class);
                    getViewState().onErrorResponse(obj);

                }

           }catch (IOException e){
               Log.d(TAG, "error parsing json  io "+e.getMessage());
           }


        }, e -> {
           //прячем прогресс бар
            getViewState().hideProgress();
            getViewState().onBadInternetConnection();
        });

    }


}


