package com.patika.subscriptionservice.controller;

import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.producer.log.LogProducer;
import com.patika.subscriptionservice.service.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubscriptionController.class)
class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriptionService subscriptionService;

    @MockBean
    private LogProducer logProducer;


    @Test
    void testFindCurrentSubscription_sucsessfully() throws Exception {
        Long userId = 1L;
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        when(subscriptionService.findCurrentSubscription(userId)).thenReturn(subscriptionResponse);

        mockMvc.perform(get("/api/v1/subscriptions/current/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
        verify(subscriptionService, times(1)).findCurrentSubscription(userId);
    }

    @Test
    void testfindAllByUserId_sucsessfully() throws Exception {
        Long userId = 1L;
        when(subscriptionService.findAllByUser(userId)).thenReturn(List.of(new SubscriptionResponse(),new SubscriptionResponse()));

        mockMvc.perform(get("/api/v1/subscriptions/secure/user")
                        .header("x-auth-user-id",userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(subscriptionService, times(1)).findAllByUser(userId);
    }
}