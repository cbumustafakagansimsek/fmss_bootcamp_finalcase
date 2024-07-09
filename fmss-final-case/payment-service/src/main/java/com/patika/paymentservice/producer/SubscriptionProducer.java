package com.patika.paymentservice.producer;

import com.patika.paymentservice.config.RabbitProducerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SubscriptionProducer {

    private final AmqpTemplate rabbitTemplate;
    private final RabbitProducerConfig rabbitProducerConfig;

    public void sendSubscription(Long userId) {
        rabbitTemplate.convertAndSend(rabbitProducerConfig.getExchange(), rabbitProducerConfig.getRoutingKey(), userId);
        log.info("Subscription sent. exchange:{}", rabbitProducerConfig.getExchange());
    }

}
