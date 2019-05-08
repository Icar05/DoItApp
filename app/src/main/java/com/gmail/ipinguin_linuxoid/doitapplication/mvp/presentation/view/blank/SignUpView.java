package com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank;


import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.signUpJson.RegistrationErrorJson;

public interface SignUpView extends BaseView  {

    void onSuccessResponse();
    void onErrorResponse(RegistrationErrorJson json);

}
