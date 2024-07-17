package com.patika.paymentservice.producer;

import com.patika.paymentservice.config.RabbitProducerConfig;
import com.patika.paymentservice.producer.dto.SubscriptionQueueDto;
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

    public void sendSubscription(SubscriptionQueueDto dto) {
        rabbitTemplate.convertAndSend(rabbitProducerConfig.getExchange(), rabbitProducerConfig.getRoutingKey(), dto);
        log.info("Subscription sent. exchange:{}", rabbitProducerConfig.getExchange());
    }

}
