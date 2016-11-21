package com.negah.telescope.app.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by emad on 11/16/2016.
 */
public class Network
{
    private static Retrofit retrofit;
    public  static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl("http://www.negahgames.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
