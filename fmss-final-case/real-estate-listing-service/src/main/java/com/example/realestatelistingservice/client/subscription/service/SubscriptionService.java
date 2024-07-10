package com.example.realestatelistingservice.client.subscription.service;

import com.example.realestatelistingservice.client.subscription.SubscriptionClient;
import com.example.realestatelistingservice.client.subscription.response.SubscriptionResponse;
import com.example.realestatelistingservice.exception.ClientException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {

    private final SubscriptionClient subscriptionClient;

    public SubscriptionResponse findCurrentSubscription(Long id){
        try {
            ResponseEntity<SubscriptionResponse> response = subscriptionClient.findCurrentSubscription(id);
            return response.getBody();

        }catch (FeignException e){
            throw new ClientException(e.contentUTF8());
        }
    }
}
