package com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.service.jsonObjects.loginJson;

/**
 * json error
 */

public class LoginErrorJson {

        private String error;

        public String getError ()
        {
            return error;
        }

        public void setError (String error)
        {
            this.error = error;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [error = "+error+"]";
        }

}
