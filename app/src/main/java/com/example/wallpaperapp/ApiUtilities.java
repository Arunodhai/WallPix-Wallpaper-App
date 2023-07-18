package com.example.wallpaperapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    private static  Retrofit retrofit = null;
    public static final String API="563492ad6f917000010000010c20091d35984bee8ef64b823e34e622";

    public static ApiInterface getApiInterface(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit.create((ApiInterface.class));
    }

}
