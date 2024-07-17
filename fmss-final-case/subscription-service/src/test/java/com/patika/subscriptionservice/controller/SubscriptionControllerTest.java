package com.patika.subscriptionservice.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.service.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(SubscriptionController.class)
class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriptionService subscriptionService;



    @Test
    void testFindCurrentSubscription_Success() throws Exception {
        Long userId = 1L;
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        when(subscriptionService.findCurrentSubscription(userId)).thenReturn(subscriptionResponse);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/subscriptions/current/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
        verify(subscriptionService, times(1)).findCurrentSubscription(userId);
    }
}