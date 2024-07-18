package com.example.adservice.producer;

import com.example.adservice.config.RabbitProducerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AdActivationProducer {

    private final AmqpTemplate rabbitTemplate;
    private final RabbitProducerConfig rabbitProducerConfig;

    public void sendAdActivation(Long userId) {
        rabbitTemplate.convertAndSend(rabbitProducerConfig.getExchange(), rabbitProducerConfig.getRoutingKey(), userId);
        log.info("Subscription sent exchange:{}", rabbitProducerConfig.getExchange());
    }

}
