package com.example.adservice.controller;

import com.example.adservice.dto.request.AdSearchRequest;
import com.example.adservice.dto.request.DetachedHouseRequest;
import com.example.adservice.dto.request.FlatRequest;
import com.example.adservice.dto.request.VillaRequest;
import com.example.adservice.model.AdStatus;
import com.example.adservice.service.AdService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(AdController.class)
public class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdService adService;
    @Test
    void saveFlat_successfully() throws Exception{
        FlatRequest request = new FlatRequest();
        request.setTitle("Test");
        request.setDescription("Test");
        request.setSize(1);
        request.setProvince("Test");
        request.setDistrict("Test");
        request.setAmount(BigDecimal.valueOf(1));
        request.setNumberOfRooms(1);
        request.setNumberOfLivingRooms(1);
        request.setNumberOfFloors(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/api/v1/ads/secure/flat")
                        .header("x-auth-user-id",1L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(adService,times(1)).save(any(FlatRequest.class),anyLong());


    }

    @Test
    void saveVilla_successfully() throws Exception{
        VillaRequest request = new VillaRequest();
        request.setTitle("Test");
        request.setDescription("Test");
        request.setSize(1);
        request.setProvince("Test");
        request.setDistrict("Test");
        request.setAmount(BigDecimal.valueOf(1));
        request.setNumberOfRooms(1);
        request.setNumberOfLivingRooms(1);
        request.setNumberOfFloors(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/api/v1/ads/secure/villa")
                        .header("x-auth-user-id",1L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(adService,times(1)).save(any(VillaRequest.class),anyLong());

    }

    @Test
    void saveDetachedHouse_successfully() throws Exception{
        DetachedHouseRequest request = new DetachedHouseRequest();
        request.setTitle("Test");
        request.setDescription("Test");
        request.setSize(1);
        request.setProvince("Test");
        request.setDistrict("Test");
        request.setAmount(BigDecimal.valueOf(1));
        request.setNumberOfRooms(1);
        request.setNumberOfLivingRooms(1);
        request.setNumberOfFloors(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/api/v1/ads/secure/detached-house")
                        .header("x-auth-user-id",1L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(adService,times(1)).save(any(DetachedHouseRequest.class),anyLong());

    }

    @Test
    void updateStatus_successfully() throws Exception{

        mockMvc.perform(put("/api/v1/ads/secure/status")
                        .header("x-auth-user-id",1L)
                        .param("status","ACTIVE")
                        .param("id","1"))
                        .andExpect(status().isOk());

        verify(adService,times(1)).updateStatus(any(AdStatus.class),anyLong(),anyLong());

    }

    @Test
    void searchActive_successfully() throws Exception{

        mockMvc.perform(get("/api/v1/ads/search/active")
                        .param("page","0")
                        .param("size","10")
                        .param("sort","ASC"))
                        .andExpect(status().isOk());

        verify(adService,times(1)).searchActive(any(AdSearchRequest.class));

    }

    @Test
    void findById_successfully() throws Exception{

        mockMvc.perform(get("/api/v1/ads/{id}",1L))
                .andExpect(status().isOk());

        verify(adService,times(1)).findById(anyLong());

    }

    @Test
    void findAllByUserId_successfully() throws Exception{

        mockMvc.perform(get("/api/v1/ads/user/{userId}",1L)
                        .param("status","ACTIVE"))
                .andExpect(status().isOk());

        verify(adService,times(1)).findAllByUserId(1L, Optional.of(AdStatus.ACTIVE));

    }

    @Test
    void updateAllStatusByUser_successfully() throws Exception{

        mockMvc.perform(put("/api/v1/ads/secure/status/user")
                        .header("x-auth-user-id",1L)
                        .param("status","ACTIVE"))
                .andExpect(status().isOk());

        verify(adService,times(1)).updateAllStatusByUser(any(AdStatus.class),anyLong());

    }
}
