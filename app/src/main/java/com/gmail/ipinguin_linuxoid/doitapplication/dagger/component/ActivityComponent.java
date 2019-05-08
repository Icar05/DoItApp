package com.gmail.ipinguin_linuxoid.doitapplication.dagger.component;

import com.gmail.ipinguin_linuxoid.doitapplication.MainActivity;
import com.gmail.ipinguin_linuxoid.doitapplication.dagger.module.ActivityModule;
import com.gmail.ipinguin_linuxoid.doitapplication.dagger.module.FragmentModule;
import com.gmail.ipinguin_linuxoid.doitapplication.dagger.scopes.ActivityScope;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.AddImageFragment;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.LoginFragment;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.SingUpFragment;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.base.RequestImageFragment;

import dagger.Component;

/**
 * компонент уровня активности
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, FragmentModule.class} )
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(AddImageFragment fragment);
    void inject(LoginFragment fragment);
    void inject(SingUpFragment fragment);
    void inject(RequestImageFragment fragment);
}
