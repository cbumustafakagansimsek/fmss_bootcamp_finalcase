package com.example.realestatelistingservice.client.subscription;

import com.example.realestatelistingservice.client.subscription.response.SubscriptionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "subscription-service",path = "/api/v1/subscriptions")
public interface SubscriptionClient {

    @GetMapping("current/{userId}")
    ResponseEntity<SubscriptionResponse> findCurrentSubscription(@PathVariable Long userId);
}
