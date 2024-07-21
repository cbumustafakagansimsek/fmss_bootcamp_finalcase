package com.patika.paymentservice.service;

import com.patika.paymentservice.producer.log.LogProducer;
import com.patika.paymentservice.producer.log.LogRequest;
import com.patika.paymentservice.producer.subscription.SubscriptionProducer;
import com.patika.paymentservice.producer.subscription.dto.SubscriptionQueueDto;
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

    @Mock
    private LogProducer logProducer;
    @Test
    void paymentValidation_shouldAlwaysReturnTrue(){
        doNothing().when(logProducer).sendLog(any(LogRequest.class));
        Boolean result = paymentService.paymentValidation(1L,10);

        assertEquals(true,result);
        verify(subscriptionProducer,times(1)).sendSubscription(Mockito.any(SubscriptionQueueDto.class));
        verify(logProducer,times(1)).sendLog(any(LogRequest.class));


    }
}
