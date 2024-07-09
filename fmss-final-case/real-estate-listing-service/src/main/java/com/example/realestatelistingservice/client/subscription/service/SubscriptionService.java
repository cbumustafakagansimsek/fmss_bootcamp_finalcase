package com.example.realestatelistingservice.client.subscription.service;

import com.example.realestatelistingservice.client.subscription.SubscriptionClient;
import com.example.realestatelistingservice.client.subscription.response.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {

    private final SubscriptionClient subscriptionClient;

    public SubscriptionResponse findCurrentSubscription(Long id){
        SubscriptionResponse response = subscriptionClient.findCurrentSubscription(id).getBody();
        return response;
    }
}
