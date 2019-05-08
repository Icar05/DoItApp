package com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.ApplicationManager;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.Interactor;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.UserSettings;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.loginJson.LoginErrorJson;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank.LoginView;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {


    final String TAG = "loginPresenter";


    @Inject
    Interactor interactor;


    public LoginPresenter() {
        ApplicationManager.getInstance().getAppComponent().inject(this);
    }



    public void login(String email, String password){


        getViewState().showProgress();

        interactor.logIn(email, password).subscribe(response -> {

            //прячем прогресс бар
            getViewState().hideProgress();


            // если запрос успешный - просто приветстуем и выходим
            if(response.isSuccessful()){

                getViewState().onSuccessResponse();

            }else{ //иначе получаем джейсон обьект ошибок и отдаетм клиенту на обработку
                try {
                    Gson gson  = new Gson();
                    LoginErrorJson obj = gson.fromJson(response.errorBody().string(),LoginErrorJson.class);

                    getViewState().onErrorResponse(obj.getError());
                }catch (IOException e){
                    Log.d(TAG, "error parsing json  io "+e.getMessage());
                }
            }


        }, e -> {
            //прячем прогресс бар
            getViewState().hideProgress();
            getViewState().onBadInternetConnection();
        });

    }


    //берем пользовательские настройки у интерактора
    public UserSettings getUserSettings(){
        return interactor.getUserSettings();
    }

}
