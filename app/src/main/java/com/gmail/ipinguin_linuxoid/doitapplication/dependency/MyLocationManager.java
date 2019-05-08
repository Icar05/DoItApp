package com.gmail.ipinguin_linuxoid.doitapplication.dependency;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.gmail.ipinguin_linuxoid.doitapplication.utils.LocationException;

import java.io.IOException;

import rx.Observable;

/**
 * На этом классе вся нагрузка за
 * работу с местоположением
 */

public class MyLocationManager {

    private final int MIN_TIME_BW_UPDATES = 0;
    private final  int MIN_DISTANCE_CHANGE_FOR_UPDATES = 0 ;

    public static final int LOCATION_DENIED = 111;
    public static final int GPS_UNAVAILABLE = 222;

    public final int TIMEOUT = 20;


    final String TAG = "myLoctionManager";



     Context context;
     LocationManager locationManager;

     public MyLocationManager(Context context) {
         Log.d("singletonTest", "init MyLocationManager");
        this.context = context;

        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }



    public Observable<Location> getLocation(String url){


        return Observable.create(subscriber -> {


            Location location = getExifLocation(url);
            if(location != null){
                subscriber.onNext(location);
                subscriber.onCompleted();

            } else{

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                        locationManager.removeUpdates(this);
                        subscriber.onNext(location);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        subscriber.onError(new LocationException("provider", GPS_UNAVAILABLE));
                    }
                };


                if(checkPermission()){

                    if(isLocationEnabled()){

                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);

                        Log.d(TAG, "internet provider work");

                    }else{
                        subscriber.onError(new LocationException("provider", GPS_UNAVAILABLE));
                    }

                }else{
                    subscriber.onError(new LocationException("permissions", LOCATION_DENIED));
                }

            }

        });

    }



    public Location getDefaultLocation(){
        Location location = new Location("unknown");
        location.setLatitude(0.0);
        location.setLongitude(0.0);
        return location;
    }

    /*
      достаем локацию с эксиф
     */

    private Location getExifLocation(String url){

        ExifInterface exifInterface = null;
        Location location = null;


        try {
            exifInterface = new ExifInterface(url);
            float[] latLong = new float[2];
            if (exifInterface.getLatLong(latLong)) {

                Log.d(TAG, "exif method 1 work -> "+latLong[0]);

                location = new Location("current");
                location.setLatitude(latLong[0]);
                location.setLongitude(latLong[1]);
                return location;
            }
        } catch (IOException e) {

            Log.d(TAG, "exif second method try ..." );

            String lat_data = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String lon_data = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);

            location = new Location("current");
            location.setLatitude(Double.valueOf(lat_data));
            location.setLongitude(Double.valueOf(lon_data));


            if(lat_data != null){
                Log.d(TAG, "exif second method success! "+lat_data );
            }


            return location;

        }

        return location;
    }

    /*
      проверяем разрешения и провайдеры
      */
    private boolean checkPermission() {

        boolean permitedFine = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED;


        boolean permitedCoarse = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED;


        return permitedCoarse && permitedFine;

    }

    /*
     место находжения может не показываться еще из за отключеных провайдеров
    */
    private boolean isLocationEnabled() {

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }




}
