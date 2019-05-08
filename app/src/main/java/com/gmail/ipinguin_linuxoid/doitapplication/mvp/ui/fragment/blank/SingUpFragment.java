package com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.MainActivity;
import com.gmail.ipinguin_linuxoid.doitapplication.R;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.FragmentManagerWrapper;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.JsonErrorHelpParser;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.UserSettings;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.signUpJson.Children;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.signUpJson.RegistrationErrorJson;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank.SignUpPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank.SignUpView;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.base.RequestImageFragment;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.ErrorHandlerHelper;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.Utility;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SingUpFragment extends RequestImageFragment implements SignUpView {



    public static final String TAG = "SingUpFragment";




    @InjectPresenter
    SignUpPresenter mSignUpPresenter;

    @Inject
    FragmentManagerWrapper fmwrapper;

    @Inject
    JsonErrorHelpParser jsonErrorHelpParser;

    /*
    views
     */
    Unbinder unbinder;
    @BindView(R.id.login_progress) ProgressBar progressbar;
    @BindView(R.id.iv_ava) ImageView iv_ava;


    @BindView(R.id.til_username) TextInputLayout til_username;
    @BindView(R.id.tie_username) TextInputEditText tie_username;

    @BindView(R.id.til_email) TextInputLayout til_email;
    @BindView(R.id.tie_email) TextInputEditText tie_email;

    @BindView(R.id.til_password) TextInputLayout til_password;
    @BindView(R.id.tie_password) TextInputEditText tie_password;



    @BindView(R.id.btn_register) Button btn_register;


    @BindView(R.id.ll_root) LinearLayout ll_root;
    @BindView(R.id.email_login_form) LinearLayout ll;
    @BindView(R.id.login_form) ScrollView scrollView;




    public static SingUpFragment newInstance() {
        SingUpFragment fragment = new SingUpFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_registration, container, false);
        init(view);
        return view;
    }


    /*
     code for init views
     */
    private void init(View view) {
        unbinder = ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).getActivityComponent().inject(this);

        setTextChangeListener(til_username);
        setTextChangeListener(til_email);
        setTextChangeListener(til_password);



        //прячу клавиатуру при клике в сторону
        ll.setOnTouchListener((v, event) -> hideKeyboard(v));
        ll_root.setOnTouchListener((v, event) -> hideKeyboard(v));


        showAva(iv_ava, R.drawable.default_ava);


        //загружаем аву
        iv_ava.setOnTouchListener((v, event) -> {
            addImageDialog.show();
            return false;
        });


        btn_register.setOnClickListener(v -> performRegistration());

    }


    //убираем вывод ошибок если заполнен текст
    void setTextChangeListener(TextInputLayout til){
        til.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    til.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    void performRegistration(){


        String username = tie_username.getText().toString().trim();
        String email = tie_email.getText().toString().trim();
        String pass = tie_password.getText().toString().trim();


        if(!username.isEmpty() && !email.isEmpty() && !pass.isEmpty() && imageUrl != null ) {

            UserSettings settings = new UserSettings(username, email, pass, imageUrl);
             mSignUpPresenter.registerUser(settings);
        }else{
            ErrorHandlerHelper.onEasyError(getContext(), getString(R.string.empty_fields));
        }

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
                    ErrorHandlerHelper.onEasyError(getContext(),getString(R.string.deny_external_storage_message) );
                }
                break;


            case Utility.MY_PERMISSIONS_CAMERA:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if(helper.getType().equals(getString(R.string.make_photo))){
                        cameraIntent();
                    }



                } else {
                    ErrorHandlerHelper.onEasyError(getContext(), getString(R.string.deny_camera_message));
                }

                break;
        }
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
        fmwrapper.replaceFragment(LoginFragment.newInstance());
    }


    @Override
    public void onErrorResponse(RegistrationErrorJson json) {


          Children children = json.getChildren();

          String username_error =   jsonErrorHelpParser.getError(children.getUsername());
          String pass_error =   jsonErrorHelpParser.getError(children.getPassword());
          String email_error =   jsonErrorHelpParser.getError(children.getEmail());
          String ava_error = jsonErrorHelpParser.getError(children.getAvatar());




          if(username_error != null){
              til_username.setError(username_error);
          }

          if(pass_error != null){
              til_password.setError(pass_error);
          }

          if(email_error != null){
              til_email.setError(email_error);
          }

          if(ava_error != null){
              ErrorHandlerHelper.onEasyError(getContext(), ava_error);
          }


    }


    @Override
    public void showImage(String imageUrl) {
        showAva(iv_ava, imageUrl);
    }
}
