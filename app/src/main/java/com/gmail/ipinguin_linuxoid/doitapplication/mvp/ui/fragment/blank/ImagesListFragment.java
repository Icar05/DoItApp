package com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.MainActivity;
import com.gmail.ipinguin_linuxoid.doitapplication.R;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.DialogBuilderHelper;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.ErrorHandlerHelper;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.view.GridAdapter;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.ImageEntity;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank.ImagesListPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank.ImagesListView;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ImagesListFragment extends BaseFragment implements ImagesListView {


    public static final String TAG = "ImagesListFragment";
    private final int MIN_IMAGES_COUNT = 5;



    @InjectPresenter
    ImagesListPresenter mImagesListPresenter;


    /*
    views
     */
    Unbinder unbinder;
    @BindView(R.id.login_progress) ProgressBar progressbar;
    @BindView(R.id.ll_conent) LinearLayout ll_conent;
    @BindView(R.id.ll_emtpy) LinearLayout ll_emtpy;
    @BindView(R.id.grid) GridView gridView;

    GridAdapter adapter;





    public static ImagesListFragment newInstance() {
        ImagesListFragment fragment = new ImagesListFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }




    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_images_list, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        unbinder = ButterKnife.bind(this, view);

        adapter = new GridAdapter();
        adapter.setEmptyView(ll_emtpy);
        adapter.setParentView(gridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view1, position, id) -> {

            DialogBuilderHelper.showGridItemModale(getContext(), adapter.getItem(position));

        });


        mImagesListPresenter.getAllImages();
    }


    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }



    public void getGif(){

        if(adapter.getCount() < MIN_IMAGES_COUNT){
            ErrorHandlerHelper.onEasyError(getContext(), getString(R.string.to_few_images));
        }else{
            mImagesListPresenter.getGif();
        }


    }



    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        ((MainActivity) getActivity()).setVisibilityLogoutIcon(true);

        menu.findItem(R.id.add_image).setVisible(true);
        menu.findItem(R.id.make_gif).setVisible(true);
        menu.findItem(R.id.save_image).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }



    @Override
    public void showProgress() {
        progressbar.setVisibility(View.VISIBLE);
        ll_conent.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressbar.setVisibility(View.GONE);
        ll_conent.setVisibility(View.VISIBLE);
    }

    @Override
    public void showList(List<ImageEntity> list) {
           adapter.setData(list);
    }

    @Override
    public void showGifDialog(String gif) {
        DialogBuilderHelper.showGifDialog(getContext(), gif);
    }
}
