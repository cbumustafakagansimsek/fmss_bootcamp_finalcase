package com.patika.subscriptionservice.controller;

import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("current/{userId}")
    public ResponseEntity<SubscriptionResponse> findCurrentSubscription(@PathVariable Long userId){
        return new ResponseEntity<>(subscriptionService.findCurrentSubscription(userId), HttpStatus.OK) ;
    }
}
