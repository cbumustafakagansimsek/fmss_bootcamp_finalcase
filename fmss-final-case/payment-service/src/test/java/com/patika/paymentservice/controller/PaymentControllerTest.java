package com.patika.paymentservice.controller;

import com.patika.paymentservice.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void testPaymentValidate_succesfully() throws Exception{

        mockMvc.perform(post("/api/v1/payment/validate")
                .header("x-auth-user-id",1L)
                .param("productAmount","10"))
                .andExpect(status().isOk());

        verify(paymentService,times(1)).paymentValidation(1L,10);
    }
}
