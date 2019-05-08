package com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.ImageEntity;

import java.util.List;

@StateStrategyType(SkipStrategy.class)
public interface ImagesListView  extends BaseView  {

    void showList(List<ImageEntity> list);
    void showGifDialog(String gif);
}
