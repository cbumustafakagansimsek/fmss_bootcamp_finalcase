package com.example.realestatelistingservice.producer;

import com.example.realestatelistingservice.config.RabbitProducerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListingActivationProducer {

    private final AmqpTemplate rabbitTemplate;
    private final RabbitProducerConfig rabbitProducerConfig;

    public void sendListingActivation(Long userId) {
        rabbitTemplate.convertAndSend(rabbitProducerConfig.getExchange(), rabbitProducerConfig.getRoutingKey(), userId);
        log.info("Subscription sent exchange:{}", rabbitProducerConfig.getExchange());
    }

}
