package com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.ApplicationManager;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.Interactor;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.ImageEntity;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson.AddImageErrorJson;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson.AddImageSuccessJson;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank.AddImageView;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;

@InjectViewState
public class AddImagePresenter extends MvpPresenter<AddImageView> {




    final String TAG = "addImagePresenter";


    @Inject
    Interactor interactor;


    public AddImagePresenter() {
        ApplicationManager.getInstance().getAppComponent().inject(this);
    }



    public void sendImage(ImageEntity entity){



        getViewState().showProgress();

           //посылаем картинку на сервак
        interactor.sendImage(entity).subscribe(response -> {

            Gson gson = new Gson();

            try{

                if(response.isSuccessful()){

                    AddImageSuccessJson obj = gson.fromJson(response.body().string(),AddImageSuccessJson.class);

                    //добавляем погоду адресс и ссылку с сервера
                    entity.setAddress(obj.getParameters().getAddress());
                    entity.setWeather(obj.getParameters().getWeather());
                    entity.setImage_url(obj.getSmallImage());

                    try{ //и кешируем понятное дело
                        interactor.cashImage(entity);
                    }catch (SQLException e){
                        e.printStackTrace();
                    }

                    getViewState().onSuccessResponse();

                }else{ //иначе получаем джейсон обьект ошибок и отдаетм клиенту на обработку

                    AddImageErrorJson obj = gson.fromJson(response.errorBody().string(),AddImageErrorJson.class);
                    getViewState().onErrorResponse(obj);

                }

            }catch (IOException e){
                Log.d(TAG, "error parsing json  io "+e.getMessage());
            }


            //прячем прогресс бар
            getViewState().hideProgress();

        }, e-> {

            //прячем прогресс бар
            getViewState().hideProgress();
            getViewState().onBadInternetConnection();
        });


    }





}
