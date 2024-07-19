package com.patika.paymentservice.service;

import com.patika.paymentservice.producer.SubscriptionProducer;
import com.patika.paymentservice.producer.dto.SubscriptionQueueDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private SubscriptionProducer subscriptionProducer;

    @Test
    void paymentValidation_shouldAlwaysReturnTrue(){

        Boolean result = paymentService.paymentValidation(1L,10);

        assertEquals(true,result);
        verify(subscriptionProducer,times(1)).sendSubscription(Mockito.any(SubscriptionQueueDto.class));

    }
}
