package com.example.adservice.producer;

import com.example.adservice.config.RabbitProducerConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdActivationProducerTest {

    @InjectMocks
    private AdActivationProducer adActivationProducer;

    @Mock
    private AmqpTemplate rabbitTemplate;

    @Mock
    private RabbitProducerConfig rabbitProducerConfig;

    @Test
    void testSendSubscription_succesfully() {
        Long userId = 1L;
        Long id=1L;
        AdActivationDto dto = new AdActivationDto(id,userId);
        String exchange = "exchange";
        String routingKey = "routingKey";

        when(rabbitProducerConfig.getExchange()).thenReturn(exchange);
        when(rabbitProducerConfig.getRoutingKey()).thenReturn(routingKey);

        adActivationProducer.sendAdActivation(dto);

        verify(rabbitTemplate, times(1)).convertAndSend(exchange, routingKey, dto);
        verify(rabbitProducerConfig, times(2)).getExchange();
        verify(rabbitProducerConfig, times(1)).getRoutingKey();
    }
}
