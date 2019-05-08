package com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.base;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.gmail.ipinguin_linuxoid.doitapplication.MainActivity;
import com.gmail.ipinguin_linuxoid.doitapplication.R;

/**
 * родитель в нутри которого куча полезностей
 */

public abstract class UtilFragment extends BaseFragment {






    /*
      показываем аватарку
     */
    protected void showAva(ImageView iv, String url){


        Glide.with(getContext())
                .load(url)
                .asBitmap()
                .placeholder(setCircularImage(R.drawable.default_ava))
                .error(setCircularImage(R.drawable.default_ava))
                .centerCrop()
                .into(new BitmapImageViewTarget(iv) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                iv.setImageDrawable(circularBitmapDrawable);
            }
        });

    }

    /*
      показываем Заглушку
     */
    protected void showAva(ImageView iv, int res_id){
        Glide.with(getContext())
                .load(res_id)
                .asBitmap()
                .placeholder(setCircularImage(R.drawable.default_ava))
                .error(setCircularImage(R.drawable.default_ava))
                .centerCrop()
                .into(new BitmapImageViewTarget(iv) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        iv.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }





    //скругляем заглушку не загрузившийся аватарки
    protected RoundedBitmapDrawable setCircularImage(int id) {
        Resources res = getContext().getApplicationContext().getResources();
        Bitmap src = BitmapFactory.decodeResource(res, id);
        RoundedBitmapDrawable roundedBitmapDrawable =
                RoundedBitmapDrawableFactory.create(res, src);
        roundedBitmapDrawable.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);
        return roundedBitmapDrawable;
    }



    //прячим клавиатуру
    protected boolean hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        return false;
    }



    /*
     управляем менюшкой иконок в тулбаре
     */
    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        ((MainActivity) getActivity()).setVisibilityLogoutIcon(false);

        menu.findItem(R.id.add_image).setVisible(false);
        menu.findItem(R.id.make_gif).setVisible(false);
        menu.findItem(R.id.save_image).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }





}
