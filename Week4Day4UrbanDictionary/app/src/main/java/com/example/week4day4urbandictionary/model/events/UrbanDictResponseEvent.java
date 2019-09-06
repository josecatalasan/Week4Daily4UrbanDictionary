package com.example.week4day4urbandictionary.model.events;

import com.example.week4day4urbandictionary.model.urbandictionary.UrbanDictionaryResponse;

public class UrbanDictResponseEvent {
    UrbanDictionaryResponse urbanDictionaryResponse;

    public UrbanDictionaryResponse getUrbanDictionaryResponse() {
        return urbanDictionaryResponse;
    }

    public void setUrbanDictionaryResponse(UrbanDictionaryResponse urbanDictionaryResponse) {
        this.urbanDictionaryResponse = urbanDictionaryResponse;
    }

    public UrbanDictResponseEvent(UrbanDictionaryResponse urbanDictionaryResponse) {
        this.urbanDictionaryResponse = urbanDictionaryResponse;
    }
}
