package com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.gmail.ipinguin_linuxoid.doitapplication.MainActivity;
import com.gmail.ipinguin_linuxoid.doitapplication.R;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.FragmentManagerWrapper;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.JsonErrorHelpParser;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.MyLocationManager;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.ImageEntity;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson.AddImageErrorJson;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank.AddImagePresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank.AddImageView;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.base.RequestImageFragment;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.ErrorHandlerHelper;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.LocationException;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.Utility;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;


public class AddImageFragment extends RequestImageFragment implements AddImageView {


    public static final String TAG = "AddImageFragment";



    @InjectPresenter
    AddImagePresenter mAddImagePresenter;


    Unbinder unbinder;
    @BindView(R.id.login_progress) ProgressBar progressbar;
    @BindView(R.id.iv_ava) ImageView iv_ava;

    @BindView(R.id.et_hashtag) EditText et_hashtag;
    @BindView(R.id.et_description) EditText et_description;


    @BindView(R.id.ll_root) LinearLayout ll_root;
    @BindView(R.id.email_login_form) LinearLayout ll;
    @BindView(R.id.login_form) ScrollView scrollView;


    @Inject
    MyLocationManager myLocationManager;

    @Inject
    FragmentManagerWrapper fmwrapper;

    @Inject
    JsonErrorHelpParser jsonErrorHelpParser;



    public static AddImageFragment newInstance() {
        AddImageFragment fragment = new AddImageFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_image, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        unbinder = ButterKnife.bind(this, view);

        //прячу клавиатуру при клике в сторону
        ll.setOnTouchListener((v, event) -> hideKeyboard(v));
        ll_root.setOnTouchListener((v, event) -> hideKeyboard(v));


        iv_ava.setOnClickListener(v -> addImageDialog.show());

        ((MainActivity) getActivity()).getActivityComponent().inject(this);

    }




    //если ответ отложеный, был запрос к разрешениям
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if(helper.getType().equals(getString(R.string.make_photo)))
                        cameraIntent();

                    else if(helper.getType().equals(getString(R.string.choose_from_gallery)))
                        galleryIntent();

                } else {
                    ErrorHandlerHelper.onEasyError(getContext(),getString(R.string.deny_external_storage_message));
                }
                break;


            case  Utility.MY_PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0){

                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED){
                        save();
                    }

                }
                break;
        }
    }





    public void save(){

        String description = et_description.getText().toString();
        String hashtag = et_hashtag.getText().toString();

        if(validate(description, hashtag)){

            showProgress();

                Observable<Location> observable = myLocationManager.getLocation(imageUrl).
                        timeout(myLocationManager.TIMEOUT, TimeUnit.SECONDS,
                        Observable.just(myLocationManager.getDefaultLocation()));



                observable.subscribe(location -> {

                        mAddImagePresenter.sendImage(constructEntity(imageUrl, description, hashtag, location));
                    }, e-> {

                        hideProgress();
                        if(e instanceof LocationException){
                            ErrorHandlerHelper.handleLocationException((LocationException) e, getContext());
                        }else{
                            mAddImagePresenter.sendImage(constructEntity(imageUrl, description, hashtag,
                                            myLocationManager.getDefaultLocation())
                            );
                        }

                    });
        }

    }


    private boolean validate(String description, String hashtag){


        if(imageUrl == null){
            ErrorHandlerHelper.onEasyError(getContext(), getString(R.string.empty_image));
            return false;

        }else {

            if (!description.trim().isEmpty() && !hashtag.trim().isEmpty()) {
                return true;

            }else {
                ErrorHandlerHelper.onEasyError(getContext(), getString(R.string.empty_fields));
                return false;
            }
        }

    }


    private ImageEntity constructEntity(String currentImageUrl, String description, String hashtag, Location location){
        ImageEntity entity = new ImageEntity();
        entity.setDescription(description);
        entity.setHashtag(hashtag);
        entity.setImage_url(currentImageUrl);
        entity.setLatitude(location.getLatitude());
        entity.setLongitude(location.getLongitude());
        return entity;
    }


    @Override
    public void showImage(String imageUrl) {
        Glide.with(getContext())
                .load(new File(imageUrl))
                .placeholder(R.drawable.default_img)
                .error(R.drawable.default_img)
                .into(iv_ava);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.save_image).setVisible(true);

    }



    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }



    @Override
    public void showProgress() {
        progressbar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressbar.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onSuccessResponse() {
        fmwrapper.popBackStack();
    }

    @Override
    public void onErrorResponse(AddImageErrorJson json) {

        String error = jsonErrorHelpParser.getError(json);
        ErrorHandlerHelper.onEasyError(getContext(), error);

    }
}
