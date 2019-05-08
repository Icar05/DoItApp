package com.gmail.ipinguin_linuxoid.doitapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.gmail.ipinguin_linuxoid.doitapplication.dagger.component.ActivityComponent;
import com.gmail.ipinguin_linuxoid.doitapplication.dagger.component.DaggerActivityComponent;
import com.gmail.ipinguin_linuxoid.doitapplication.dagger.module.ActivityModule;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.FragmentManagerWrapper;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.Interactor;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.UserSettings;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.AddImageFragment;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.ImagesListFragment;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.LoginFragment;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.SingUpFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.icon)
    ImageView icon;

    Unbinder unbinder;



    @Inject
    Interactor interactor;

    @Inject
    FragmentManagerWrapper fmwrapper;

    ActivityComponent activityComponent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);



        initActivityComponent();


        icon.setOnClickListener(v -> logOut());

        try{
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }catch (NullPointerException e){
            e.getMessage();
        }

         startFragment();


    }

    private void initActivityComponent() {

        activityComponent = DaggerActivityComponent.builder()
                .appComponent(ApplicationManager.getInstance().getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);

    }


    //возвращаем компонент для внедрений
    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }



    //вылогиниться
    private void logOut(){
        fmwrapper.popBackStack();
    }



    //иконка вылогиниться
    public void setVisibilityLogoutIcon(boolean value){
         icon.setVisibility(value ? View.VISIBLE : View.GONE);
    }



    private void startFragment() {
        UserSettings settings = interactor.getUserSettings();

        //были ли мы зарегестрированы или нет?
        Fragment fragment = settings.getToken().isEmpty() ? SingUpFragment.newInstance() : LoginFragment.newInstance();
        fmwrapper.replaceFragment(fragment);
    }





       /*
         кнопки меню
        */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        int id = item.getItemId();


        if(id == R.id.add_image){


            fmwrapper.replaceFragment(AddImageFragment.newInstance(),
                    fmwrapper.BACKSTACKTAG, fmwrapper.ADD_FRAGMENT_TAG);

        } else if(id == R.id.make_gif){

            ImagesListFragment fragment =
                    fmwrapper.getFragmentByTag(fmwrapper.IMAGES_LIST_TAG, ImagesListFragment.class);

            if(fragment!=null) {
                fragment.getGif();
            }

        } else if(id == R.id.save_image){
            AddImageFragment fragment =
                    fmwrapper.getFragmentByTag(fmwrapper.ADD_FRAGMENT_TAG, AddImageFragment.class);

            if(fragment!=null) {
                fragment.save();
            }
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
