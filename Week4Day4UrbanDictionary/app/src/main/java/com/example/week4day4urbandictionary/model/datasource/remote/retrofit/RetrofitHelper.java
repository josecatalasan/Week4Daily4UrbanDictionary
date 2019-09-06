package com.example.week4day4urbandictionary.model.datasource.remote.retrofit;

import com.example.week4day4urbandictionary.model.datasource.remote.retrofit.services.ObservableUrbanDictService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.week4day4urbandictionary.model.datasource.remote.retrofit.Url_Constants.BASE_URL;

public class RetrofitHelper {

    private Retrofit getRetrofitInstance(){


        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public ObservableUrbanDictService getService(){
        return getRetrofitInstance().create(ObservableUrbanDictService.class);
    }
}
