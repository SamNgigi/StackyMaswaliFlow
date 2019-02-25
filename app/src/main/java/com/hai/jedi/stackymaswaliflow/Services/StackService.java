package com.hai.jedi.stackymaswaliflow.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hai.jedi.stackymaswaliflow.Constants;
import com.hai.jedi.stackymaswaliflow.Interfaces.StackyInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StackService {
    private static final String TAG =  StackService.class.getSimpleName();



    public static StackyInterface stackApiCall(){
        Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
                        .create();
        Retrofit retrofit = new Retrofit
                                .Builder()
                                .baseUrl(Constants.STACK_BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build();
        return  retrofit.create(StackyInterface.class);
    }

}
