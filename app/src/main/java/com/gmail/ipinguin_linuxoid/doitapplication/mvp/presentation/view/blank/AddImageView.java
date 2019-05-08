package com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank;


import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson.AddImageErrorJson;

public interface AddImageView extends BaseView {


   void onSuccessResponse();
    void onErrorResponse(AddImageErrorJson json);

}
