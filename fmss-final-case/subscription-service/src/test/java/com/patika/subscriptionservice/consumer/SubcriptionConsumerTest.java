package com.patika.subscriptionservice.consumer;

import com.patika.subscriptionservice.dto.request.MultipleSubscriptionRequest;
import com.patika.subscriptionservice.service.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubcriptionConsumerTest {

    @InjectMocks
    private SubscriptionConsumer subscriptionConsumer;

    @Mock
    private SubscriptionService subscriptionService;

    @Test
    void testSendSubscription_succesfully(){
        MultipleSubscriptionRequest request = new MultipleSubscriptionRequest();
        doNothing().when(subscriptionService).saveMultiple(request);

        subscriptionConsumer.sendSubscription(request);

        verify(subscriptionService,times(1)).saveMultiple(request);

    }

}
