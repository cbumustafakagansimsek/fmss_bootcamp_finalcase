package com.patika.paymentservice.producer.log;

import com.patika.paymentservice.config.RabbitLogProducerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class LogProducer {

    private final AmqpTemplate rabbitTemplate;
    private final RabbitLogProducerConfig rabbitProducerConfig;

    public void sendLog(LogRequest logReq) {
        LogDto dto = new LogDto(logReq.getService(), logReq.getLevel().name(), logReq.getClassOfService(), logReq.getMessage(), logReq.getTime());
        log.info("log sent exchange:{}", rabbitProducerConfig.getExchange());
        rabbitTemplate.convertAndSend(rabbitProducerConfig.getExchange(), rabbitProducerConfig.getRoutingKey(), dto);
    }
}
