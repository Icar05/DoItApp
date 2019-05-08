package com.gmail.ipinguin_linuxoid.doitapplication.dependency;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Помагает находить картинки
 */

public class LoadImageHelper {

    final String TAG = "loadImageHelper";


    public LoadImageHelper() {
        Log.d("singletonTest", "init LoadImageHelper");
    }

    /*
         помогает найти реальный путь до картинки
         */
    public String getRealPathFromURI(Intent data, Context context) {

        Uri uri = data.getData();

        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        if(uri != null){
            Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);

            if(cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                return picturePath;
            }else{
                return uri.getPath();
            }
        }

        return null;
    }






    /*
    находит путь из камеры
    */
    public String onCaptureImageResult(Intent intent) {

        Bitmap thumbnail = (Bitmap) intent.getExtras().get("data");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;

        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return  destination.getAbsolutePath();
    }


}
