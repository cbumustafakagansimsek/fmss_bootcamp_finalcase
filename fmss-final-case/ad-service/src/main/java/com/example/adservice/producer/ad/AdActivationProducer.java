package com.example.adservice.producer.ad;

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

    public void sendAdActivation(AdActivationDto dto) {
        rabbitTemplate.convertAndSend(rabbitProducerConfig.getExchange(), rabbitProducerConfig.getRoutingKey(), dto);
        log.info("ad activation sent exchange:{}", rabbitProducerConfig.getExchange());
    }

}
