package com.patika.paymentservice.service;

import com.patika.paymentservice.producer.SubscriptionProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final SubscriptionProducer subscriptionProducer;
    public boolean paymentValidation(Long userId){
        log.info("request for payment validate by userId:{}",userId);
        subscriptionProducer.sendSubscription(userId);
        return true;
    }
}
