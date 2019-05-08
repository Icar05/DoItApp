package com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.base;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.ErrorHandlerHelper;

/**
 * Здесь будут обработчики всего
 * что пригрдиться всем фрагментам
 */

public class BaseFragment extends MvpAppCompatFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onBadInternetConnection() {
        ErrorHandlerHelper.onNetworkError(getContext());
    }

}
