package com.example.adservice.factory;


import com.example.adservice.model.Ad;

public interface AdFactory<T> {

    Ad createAd(T object, Long userId);
}
