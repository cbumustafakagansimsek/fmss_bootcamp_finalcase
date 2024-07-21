package com.patika.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.userservice.dto.request.LoginRequest;
import com.patika.userservice.dto.request.UserRequest;
import com.patika.userservice.producer.log.LogProducer;
import com.patika.userservice.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private LogProducer logProducer;

    @Test
    @WithMockUser(username = "test@mail.com", roles = "INITIAL")
    void testLogin_successfully() throws Exception{
        LoginRequest loginRequest =LoginRequest.builder()
                .mail("test@mail.com")
                .password("password")
                .build();

        ObjectMapper objectMapper =new ObjectMapper();
        String body = objectMapper.writeValueAsString(loginRequest);
        mockMvc.perform(post("/api/v1/auth/login")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isAccepted());

        verify(authService,times(1)).login(any(LoginRequest.class));
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = "INITIAL")
    void testRegister_successfully() throws Exception{
        UserRequest userRequest = UserRequest.builder()
                .name("Test")
                .surname("Surname")
                .password("password")
                .mail("test@mail.com")
                .build();
        ObjectMapper objectMapper =new ObjectMapper();
        String body = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(post("/api/v1/auth/register")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isCreated());

        verify(authService,times(1)).register(any(UserRequest.class));
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = "INITIAL")
    void testValidateToken_successfully() throws Exception{

        mockMvc.perform(post("/api/v1/auth/validateToken")
                        .param("token","token")
                        .contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isOk());

        verify(authService,times(1)).validateToken(any(String.class));
    }
}
