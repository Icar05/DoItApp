package com.gmail.ipinguin_linuxoid.doitapplication;

import android.app.Application;

import com.gmail.ipinguin_linuxoid.doitapplication.dagger.component.AppComponent;
import com.gmail.ipinguin_linuxoid.doitapplication.dagger.component.DaggerAppComponent;
import com.gmail.ipinguin_linuxoid.doitapplication.dagger.module.AppModule;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.DBManagerProvider;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.PrefManager;

/**
 * менеджер всего приложения
 */

public class ApplicationManager extends Application {


    private static ApplicationManager mInstance;
    private PrefManager pref;
    AppComponent appComponent;



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        DBManagerProvider.setHelper(getApplicationContext());

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, getPrefManager(), DBManagerProvider.getManager()))
                .build();

    }
    @Override
    public void onTerminate() {
        DBManagerProvider.releaseHelper();
        super.onTerminate();
    }




    public static synchronized ApplicationManager getInstance() {
        return mInstance;
    }


    public PrefManager getPrefManager() {
        if (pref == null) {
            pref = new PrefManager(getApplicationContext());
        }

        return pref;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
