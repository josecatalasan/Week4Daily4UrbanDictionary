package com.example.week4day4urbandictionary.model.datasource.remote.retrofit;

import android.util.Log;

import com.example.week4day4urbandictionary.model.events.UrbanDictResponseEvent;
import com.example.week4day4urbandictionary.model.urbandictionary.UrbanDictionaryResponse;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UrbanDictObserver implements Observer<UrbanDictionaryResponse> {
    private UrbanDictionaryResponse urbanDictionaryResponse;

    @Override
    public void onSubscribe(Disposable d) {
        Log.d("RxJava", "Subscribed");

    }

    @Override
    public void onNext(UrbanDictionaryResponse urbanDictionaryResponse) {
        this.urbanDictionaryResponse = urbanDictionaryResponse;
    }

    @Override
    public void onError(Throwable e) {
        Log.e("RxJava", "ERROR IN OBSERVER--> ",e );
    }

    @Override
    public void onComplete() {
        EventBus.getDefault().post(new UrbanDictResponseEvent(urbanDictionaryResponse));
    }
}
