package com.patika.subscriptionservice.consumer;


import com.patika.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubscriptionConsumer {

    private final SubscriptionService subscriptionService;
    @RabbitListener(queues = "${subscription.queue}")
    public void sendNotification(Long userId) {
            subscriptionService.save(userId);
            log.info("subscription :{}", userId);
    }



}
