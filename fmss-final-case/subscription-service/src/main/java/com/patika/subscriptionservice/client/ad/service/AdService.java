package com.patika.subscriptionservice.client.ad.service;

import com.patika.subscriptionservice.client.ad.AdClient;
import com.patika.subscriptionservice.exception.ClientException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdService {

    private final AdClient adClient;

    public void updateAllStatusById(AdStatus status,Long id){

        try {
            adClient.updateAllStatusByUser(status,id);
        }catch (FeignException exception){
            throw new ClientException(exception.contentUTF8());
        }
    }
}
