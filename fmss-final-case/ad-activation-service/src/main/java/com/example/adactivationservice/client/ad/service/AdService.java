package com.example.adactivationservice.client.ad.service;

import com.example.adactivationservice.client.ad.AdClient;
import com.example.adactivationservice.client.ad.dto.AdStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdService {
    private final AdClient adClient;

    public void updateStatus(AdStatus status, Long id){
        log.info("request to update status for status:{} by id:{}",status,id);
        adClient.updateStatus(status, id);
    }
}
