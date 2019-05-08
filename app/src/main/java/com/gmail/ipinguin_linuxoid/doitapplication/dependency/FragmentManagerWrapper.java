package com.gmail.ipinguin_linuxoid.doitapplication.dependency;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.gmail.ipinguin_linuxoid.doitapplication.R;

/**
 * Попробую скинуть на него всю нагрузку по
 * навигации между фрагментами
 */

public class FragmentManagerWrapper {

    FragmentManager fragmentManager;

    public  final String ADD_FRAGMENT_TAG = "addFragment";
    public  final String BACKSTACKTAG = "myStack";
    public  final String IMAGES_LIST_TAG = "imagesListFragment";
    public  final String SIGNUP_FRAGMENT_TAG = "signupFragment";



    public FragmentManagerWrapper(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        Log.d("singletonTest", "init FragmentManagerWrapper");
    }



    public void replaceFragment(Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }


    public void replaceFragment(Fragment fragment, String backStack, String TAG){
                 fragmentManager.beginTransaction()
                .replace(R.id.content, fragment, TAG)
                .addToBackStack(backStack)
                .commit();
    }


    public void popBackStack(){
        fragmentManager.popBackStack();
    }


    public  <T> T getFragmentByTag(String tag, Class<T> clazz) {

        try {
            return clazz.cast(fragmentManager.findFragmentByTag(tag));
        } catch(ClassCastException e) {
            return null;
        }
    }





}
