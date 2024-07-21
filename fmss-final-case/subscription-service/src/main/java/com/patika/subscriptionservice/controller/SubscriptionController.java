package com.patika.subscriptionservice.controller;

import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("current/{userId}")
    public ResponseEntity<SubscriptionResponse> findCurrentSubscription(@PathVariable Long userId){
        log.info("Rest Request to find current subscription by id:{}",userId);
        return new ResponseEntity<>(subscriptionService.findCurrentSubscription(userId), HttpStatus.OK) ;
    }

    @GetMapping("secure/user")
    public ResponseEntity<List<SubscriptionResponse>> findAllByUserId(@RequestHeader("x-auth-user-id") Long userId){
        log.info("Rest Request to find current subscription by id:{}",userId);
        return new ResponseEntity<>(subscriptionService.findAllByUser(userId), HttpStatus.OK) ;
    }
}
