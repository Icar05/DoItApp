package com.gmail.ipinguin_linuxoid.doitapplication.utils;

import android.app.AlertDialog;
import android.content.Context;

import com.gmail.ipinguin_linuxoid.doitapplication.R;

/**
 * показываем диалоговое окно  для  добавления картинок
 */

public class AddImageDialogHelper {

    Context context;

    CharSequence[] items = new CharSequence[3];
    String type;

    public String getType() {
        return type;
    }

    public AddImageDialogHelper(Context context) {
        this.context = context;

        items[0] = context.getString(R.string.make_photo);
        items[1] = context.getString(R.string.choose_from_gallery);
        items[2] = context.getString(R.string.cancel);

    }


    public interface IAddImageDialogDelegate {

        void onCameraWasChosen();
        void onGaleryWasChosen();

    }


    public AlertDialog showAddImageDialog(IAddImageDialogDelegate delegate) {


        AlertDialog dial = new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.add_ava_title))
                .setCancelable(false)
                .setItems(items, (dialog, item) -> {


            type = items[item].toString();
                    //разрешено ли делать манипуляции?
            boolean result = Utility.checkPermission(context);


            //если камера
            if (items[item].equals(items[0])) {
                if (result)
                    delegate.onCameraWasChosen();

                //если галерея
            } else if (items[item].equals(items[1])) {
                if (result)
                    delegate.onGaleryWasChosen();

            } else if (items[item].equals(items[2])) {
                dialog.dismiss();
            }

        }).create();
        dial.setCanceledOnTouchOutside(false);

        return dial;


    }


}
