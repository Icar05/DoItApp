package com.gmail.ipinguin_linuxoid.doitapplication.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import com.gmail.ipinguin_linuxoid.doitapplication.R;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.MyLocationManager;


/**
 * На него я свалю всю ответственность за
 * обработку разных ошибок
 */

public class ErrorHandlerHelper {



    /*
         обработка отсутсвия интернета
         */
    public static void onNetworkError(final Context context){
        AlertDialog dial = DialogBuilderHelper.createtDialog(
                context,
                context.getString(R.string.internet_error),
                context.getString(R.string.internet_error_message),
                () -> {
                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    context.startActivity(intent);
                }
        );
        dial.show();
    }


    /*
      обработка отключеного gps провайдера
     */
    public static void onProviderDisabled(final Context context) {

        AlertDialog dial = DialogBuilderHelper.createtDialog(
                context,
                context.getString(R.string.provider),
                context.getString(R.string.provider_error_text),
                () -> {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(intent);
                }
        );
        dial.show();
    }

    /*
     обработка ошибки разрешений
    */
    public static void onPermissionError(final Context context) {

        AlertDialog dial = DialogBuilderHelper.createtDialog(
                context,
                context.getString(R.string.permission),
                context.getString(R.string.permissions_error_text),
                () -> {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                            Utility.MY_PERMISSIONS_REQUEST_LOCATION);
                }
        );
        dial.show();
    }

    public static void onEasyError(final Context context, String error) {

        AlertDialog dial = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.error))
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.ok), (dialog, which) ->
                {}).create();

        dial.setCanceledOnTouchOutside(false);
        dial.show();
    }




    /*
     обработка ошибки разрешений
    */
    public static void onPermissionErrorCamera(final Context context) {

        AlertDialog dial = DialogBuilderHelper.createtDialog(
                context,
                context.getString(R.string.permission),
                context.getString(R.string.permissions_error_camera),
                () -> {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[] {
                                    Manifest.permission.CAMERA},
                            Utility.MY_PERMISSIONS_CAMERA);
                }
        );
        dial.show();
    }


    public static void handleLocationException(LocationException e, Context context){

        int code = e.getError_code();

        if(code == MyLocationManager.GPS_UNAVAILABLE){
            onProviderDisabled(context);
        }else if(code == MyLocationManager.LOCATION_DENIED){
            onPermissionError(context);
        }
    }


}
