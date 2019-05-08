package com.gmail.ipinguin_linuxoid.doitapplication.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gmail.ipinguin_linuxoid.doitapplication.R;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.view.SquareImageView;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.ImageEntity;



/**
 * класс создающий диалоговые окна
 */

public class DialogBuilderHelper {


    public interface OnPositiveClick{
        void onPositiveClick();
    }




    public static AlertDialog createtDialog(Context context, String title, String message , final OnPositiveClick delegat) {

        AlertDialog dial = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(context.getString(R.string.close), (dialog, which) -> {

                })
                .setPositiveButton(context.getString(R.string.allow), (dialog, which) -> delegat.onPositiveClick()).create();

        dial.setCanceledOnTouchOutside(false);
        return dial;

    }

    public static void showGifDialog(Context context, String url) {


        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        View alertLayout = inflater.inflate(R.layout.gif_item, null);
        final SquareImageView  iv_image = (SquareImageView) alertLayout.findViewById(R.id.iv_image);



        Glide.with(context)
                .load(url)
                .asGif()
                .centerCrop()
                .placeholder(R.drawable.waiting)
                .error(R.drawable.default_img)
                .crossFade()
                .listener(new RequestListener<String, GifDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
                        Log.d("glide", "glide exc "+e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.d("glide", "glide done "+resource);
                        return false;
                    }
                })
                .into(iv_image);

        AlertDialog dial = new AlertDialog.Builder(context).create();

         dial.setView(alertLayout);
         dial.show();

    }


    public static void showGridItemModale(Context context, ImageEntity entity){

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        View alertLayout = inflater.inflate(R.layout.grid_item, null);
        final SquareImageView  iv_image = (SquareImageView) alertLayout.findViewById(R.id.iv_image);
        final TextView tv_weather = (TextView) alertLayout.findViewById(R.id.tv_weather);
        final TextView tv_address = (TextView) alertLayout.findViewById(R.id.tv_address);

        Glide.with(context)
                .load(entity.getImage_url())
                .placeholder(R.drawable.default_img)
                .error(R.drawable.default_img)
                .into(iv_image);


        tv_weather.setText(entity.getWeather());
        tv_address.setText(entity.getAddress());


        AlertDialog dial = new AlertDialog.Builder(context).create();
        dial.setView(alertLayout);
        dial.show();


    }




}
