package com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.ApplicationManager;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.Interactor;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.ImageEntity;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.getGIfJson.GifJson;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.getImagesJson.ImagesJson;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank.ImagesListView;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class ImagesListPresenter extends MvpPresenter<ImagesListView> {


    final String TAG = "image_presenter";


    @Inject
    Interactor interactor;


    public ImagesListPresenter() {
        ApplicationManager.getInstance().getAppComponent().inject(this);
    }


    /*
     получаем гифку для вывода
     */
    public void getGif(){

        getViewState().showProgress();


        interactor.getGif().subscribe(response-> {


            if(response.isSuccessful()){

                try{
                    Gson gson = new Gson();
                    String gifUrl =  gson.fromJson(response.body().string(),GifJson.class).getGif();
                    getViewState().showGifDialog(gifUrl);
                }catch (IOException e){
                    Log.d(TAG, "error parsing json "+e.getMessage());
                }
            }

            getViewState().hideProgress();

        }, e-> {
            getViewState().hideProgress();
            getViewState().onBadInternetConnection();
        });

    }


    /*
       получаем все картинки
     */
    public void getAllImages(){

        getViewState().showProgress();

        interactor.getAllImages().subscribe(response-> {

            if(response.isSuccessful()){

                try{
                    Gson gson = new Gson();
                    ImagesJson json =  gson.fromJson(response.body().string(),ImagesJson.class);

                    try {
                        interactor.reSaveImages(json.getImages());
                    }catch (SQLException e){
                        Log.d(TAG, "sql exc "+e.getMessage());
                    }


                }catch (IOException e){
                    Log.d(TAG, "error parsing json "+e.getMessage());
                }
            }

            getViewState().hideProgress();
            getViewState().showList(getContent());

        }, e-> {
            getViewState().hideProgress();
            getViewState().showList(getContent());
            getViewState().onBadInternetConnection();
        });


    }



    /*
     берем все картинки из кеша
     */
    private List<ImageEntity> getContent(){

        try{
           return interactor.getImagesFromCash();
        }catch (SQLException e){
            Log.d(TAG, "Sql ex -> "+e.getMessage());
            return new ArrayList<>();
        }
    }





}
