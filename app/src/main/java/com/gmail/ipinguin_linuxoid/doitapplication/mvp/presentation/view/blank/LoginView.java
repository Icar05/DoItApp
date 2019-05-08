package com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


@StateStrategyType(SkipStrategy.class)
public interface LoginView extends BaseView  {

    void onSuccessResponse();
    void onErrorResponse(String message);

}
