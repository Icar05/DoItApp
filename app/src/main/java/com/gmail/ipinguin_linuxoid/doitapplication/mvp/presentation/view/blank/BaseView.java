package com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank;

import com.arellomobile.mvp.MvpView;

/**
 * Базовые методы нужные во всех вью
 */

public interface BaseView  extends MvpView {
    void onBadInternetConnection();
    void showProgress();
    void hideProgress();
}
