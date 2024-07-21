package com.patika.paymentservice.service;

import com.patika.paymentservice.producer.log.LogProducer;
import com.patika.paymentservice.producer.log.LogRequest;
import com.patika.paymentservice.producer.subscription.SubscriptionProducer;
import com.patika.paymentservice.producer.subscription.dto.SubscriptionQueueDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final SubscriptionProducer subscriptionProducer;

    private final LogProducer logProducer;
    public boolean paymentValidation(Long userId,int productAmount){

        log.info("request for payment validate by userId:{}",userId);
        logProducer.sendLog(new LogRequest("[payment-service]",
                Level.INFO,
                "paymentservice",
                "request for payment validate by userId:"+userId,
                new Date()));


        SubscriptionQueueDto dto = SubscriptionQueueDto.builder()
                .UserId(userId)
                .productAmount(productAmount)
                .build();
        subscriptionProducer.sendSubscription(dto);
        return true;
    }

}
