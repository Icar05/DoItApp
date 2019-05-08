package com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.MainActivity;
import com.gmail.ipinguin_linuxoid.doitapplication.R;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.FragmentManagerWrapper;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.UserSettings;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.presenter.blank.LoginPresenter;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.presentation.view.blank.LoginView;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.base.UtilFragment;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.ErrorHandlerHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginFragment extends UtilFragment implements LoginView {


    public static final String TAG = "LoginFragment";


    @InjectPresenter
    LoginPresenter mLoginPresenter;

    /*
    views
     */
    Unbinder unbinder;
    @BindView(R.id.login_progress) ProgressBar progressbar;
    @BindView(R.id.iv_ava) ImageView iv_ava;

    @BindView(R.id.email) EditText et_email;
    @BindView(R.id.password) EditText et_password;
    @BindView(R.id.btn_register) Button btn_register;

    @BindView(R.id.ll_root) LinearLayout ll_root;
    @BindView(R.id.email_login_form) LinearLayout ll;
    @BindView(R.id.login_form) ScrollView scrollView;
    @BindView(R.id.tv_to_register) TextView tv_to_register;

    @Inject FragmentManagerWrapper fmwrapper;



    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        return view;
    }


    /*
   code for init views
   */
    private void init(View view) {
        unbinder = ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).getActivityComponent().inject(this);

        //заполняем данные авторизованого юзера ...
        UserSettings settings = mLoginPresenter.getUserSettings();
        showAva(iv_ava, settings.getUrl());
        et_email.setText(settings.getEmail());
        et_password.setText(settings.getPass());
        et_email.setSelection(et_email.getText().length());



        //прячу клавиатуру при клике в сторону
        ll.setOnTouchListener((v, event) -> hideKeyboard(v));
        ll_root.setOnTouchListener((v, event) -> hideKeyboard(v));



        //обработчик логина
        btn_register.setOnClickListener(v -> logIn());
        tv_to_register.setOnClickListener(v -> toRegistration());


    }


    private void logIn(){
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();


        if(!email.isEmpty() && !password.isEmpty()){
            mLoginPresenter.login(email, password);
        }else{
            ErrorHandlerHelper.onEasyError(getContext(), getString(R.string.empty_fields));
        }
    }


    void toRegistration(){
        fmwrapper.replaceFragment(
                SingUpFragment.newInstance(), fmwrapper.BACKSTACKTAG, fmwrapper.SIGNUP_FRAGMENT_TAG);
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
        fmwrapper.replaceFragment(
                ImagesListFragment.newInstance(), fmwrapper.BACKSTACKTAG,fmwrapper.IMAGES_LIST_TAG );
    }

    @Override
    public void onErrorResponse(String message) {
          ErrorHandlerHelper.onEasyError(getContext(), message);
    }

}
