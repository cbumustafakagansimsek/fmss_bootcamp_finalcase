package com.patika.paymentservice.service;

import com.patika.paymentservice.producer.SubscriptionProducer;
import com.patika.paymentservice.producer.dto.SubscriptionQueueDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final SubscriptionProducer subscriptionProducer;
    public boolean paymentValidation(Long userId,int productAmount){

        log.info("request for payment validate by userId:{}",userId);
        SubscriptionQueueDto dto = SubscriptionQueueDto.builder()
                .UserId(userId)
                .productAmount(productAmount)
                .build();
        subscriptionProducer.sendSubscription(dto);
        return true;
    }

}
