package com.gmail.ipinguin_linuxoid.doitapplication.utils;

/**
 * here is exceptions location
 */

public class LocationException extends Exception {

    private int error_code;

    public LocationException(String message, int error_code){
        super(message);
        this.error_code = error_code;

    }

    public LocationException(String message) {
        super(message);
    }


    public int getError_code() {
        return error_code;
    }
}
