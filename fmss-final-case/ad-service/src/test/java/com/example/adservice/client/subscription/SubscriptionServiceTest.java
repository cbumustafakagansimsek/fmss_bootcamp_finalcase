package com.example.adservice.client.subscription;

import com.example.adservice.client.subscription.response.SubscriptionResponse;
import com.example.adservice.client.subscription.service.SubscriptionService;
import com.example.adservice.exception.ClientException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTest {
    @InjectMocks
    private SubscriptionService subscriptionService;

    @Mock
    private SubscriptionClient subscriptionClient;

    private static final Long USER_ID=1L;

    @Test
    void findCurrentSubscription_successfully(){
        ResponseEntity<SubscriptionResponse> responseEntity = ResponseEntity.ok(new SubscriptionResponse());

        when(subscriptionClient.findCurrentSubscription(USER_ID)).thenReturn(responseEntity);

        subscriptionService.findCurrentSubscription(USER_ID);


        verify(subscriptionClient,times(1)).findCurrentSubscription(USER_ID);
    }

    @Test
    void findCurrentSubscription_failure(){

        when(subscriptionClient.findCurrentSubscription(USER_ID)).thenThrow(new ClientException("Error message"));
        ClientException exception = assertThrows(ClientException.class,()->subscriptionService.findCurrentSubscription(USER_ID));

        assertEquals("Error message", exception.getMessage());

        verify(subscriptionClient,times(1)).findCurrentSubscription(USER_ID);
    }

}
