package com.patika.paymentservice.producer;

import com.patika.paymentservice.config.RabbitProducerConfig;
import com.patika.paymentservice.producer.subscription.SubscriptionProducer;
import com.patika.paymentservice.producer.subscription.dto.SubscriptionQueueDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionProducerTest {

    @InjectMocks
    private SubscriptionProducer subscriptionProducer;

    @Mock
    private AmqpTemplate rabbitTemplate;

    @Mock
    private RabbitProducerConfig rabbitProducerConfig;

    @Test
    void testSendSubscription_succesfully() {
        SubscriptionQueueDto dto = SubscriptionQueueDto.builder()
                .UserId(1L)
                .productAmount(100)
                .build();

        String exchange = "exchange";
        String routingKey = "routingKey";

        when(rabbitProducerConfig.getExchange()).thenReturn(exchange);
        when(rabbitProducerConfig.getRoutingKey()).thenReturn(routingKey);

        subscriptionProducer.sendSubscription(dto);

        verify(rabbitTemplate, times(1)).convertAndSend(exchange, routingKey, dto);
        verify(rabbitProducerConfig, times(2)).getExchange();
        verify(rabbitProducerConfig, times(1)).getRoutingKey();
    }
}
