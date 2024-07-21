package com.patika.userservice.controller;


import com.patika.userservice.producer.log.LogProducer;
import com.patika.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private LogProducer logProducer;
    private static final Long USER_ID = 1L;


    @Test
    @WithMockUser(username = "test@mail.com", roles = "INITIAL")
    void testFindById_successfully() throws Exception {

        mockMvc.perform(get("/api/v1/user/{id}", USER_ID).with(csrf()))
                .andExpect(status().isOk());

        verify(userService, times(1)).findById(USER_ID);
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = "INITIAL")
    void testUpdateRoleAsSubscribed_successfully() throws Exception {


        mockMvc.perform(put("/api/v1/user/role/subscribed/{id}", USER_ID).with(csrf()))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateRoleAsSubscribed(USER_ID);
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = "INITIAL")
    void testUpdateRoleAsInitial_successfully() throws Exception {


        mockMvc.perform(put("/api/v1/user/role/initial/{id}", USER_ID).with(csrf()))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateRoleAsInitial(USER_ID);
    }
}