package com.gmail.ipinguin_linuxoid.doitapplication.dependency;


import android.util.Log;

import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson.AddImageErrorJson;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.addImageJson.Children;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.jsonErrorChild;

import java.util.ArrayList;
import java.util.List;

/**
 * Попробуем разбирать обьекты
 */

public class JsonErrorHelpParser {

    public JsonErrorHelpParser() {
        Log.d("singletonTest", "init JsonErrorHelpParser");

    }

    public String getError(jsonErrorChild child){
        return child.getError();
    }


    public String getError(AddImageErrorJson json){


        List<jsonErrorChild> list = new ArrayList<>();
        Children children = json.getChildren();

        list.add(children.getImage());
        list.add(children.getDescription());
        list.add(children.getHashtag());
        list.add(children.getLatitude());
        list.add(children.getLongitude());


        for (jsonErrorChild child: list) {

            String temp = child.getError();
            if(temp != null){
                return temp;
            }

        }

        return "unknown error";
    }

}
