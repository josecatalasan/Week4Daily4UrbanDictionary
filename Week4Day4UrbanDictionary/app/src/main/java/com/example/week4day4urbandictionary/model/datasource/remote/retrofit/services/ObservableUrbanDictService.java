package com.example.week4day4urbandictionary.model.datasource.remote.retrofit.services;

import com.example.week4day4urbandictionary.model.urbandictionary.UrbanDictionaryResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.week4day4urbandictionary.model.datasource.remote.retrofit.Url_Constants.HOST_HEADER1;
import static com.example.week4day4urbandictionary.model.datasource.remote.retrofit.Url_Constants.HOST_HEADER2;
import static com.example.week4day4urbandictionary.model.datasource.remote.retrofit.Url_Constants.KEY_HEADER1;
import static com.example.week4day4urbandictionary.model.datasource.remote.retrofit.Url_Constants.KEY_HEADER2;
import static com.example.week4day4urbandictionary.model.datasource.remote.retrofit.Url_Constants.PATH_API;
import static com.example.week4day4urbandictionary.model.datasource.remote.retrofit.Url_Constants.QUERY_TERM;

public interface ObservableUrbanDictService {

    @Headers({HOST_HEADER1 + ": " + HOST_HEADER2,
            KEY_HEADER1 + ": " + KEY_HEADER2})
    @GET(PATH_API)
    Observable<UrbanDictionaryResponse> getDefinitions(@Query(QUERY_TERM)String term);
}
