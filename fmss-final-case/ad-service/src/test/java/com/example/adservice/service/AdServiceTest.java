package com.example.adservice.service;


import com.example.adservice.client.subscription.response.SubscriptionResponse;
import com.example.adservice.client.subscription.service.SubscriptionService;
import com.example.adservice.converter.AdConverter;
import com.example.adservice.dto.request.AdSearchRequest;
import com.example.adservice.dto.request.FlatRequest;
import com.example.adservice.dto.response.AdResponse;
import com.example.adservice.dto.response.SearchResponse;
import com.example.adservice.exception.AdNotFoundException;
import com.example.adservice.exception.ExhaustedAdLimitException;
import com.example.adservice.factory.impl.FlatFactory;
import com.example.adservice.model.Ad;
import com.example.adservice.model.AdStatus;
import com.example.adservice.producer.ad.AdActivationDto;
import com.example.adservice.producer.ad.AdActivationProducer;
import com.example.adservice.producer.log.LogProducer;
import com.example.adservice.producer.log.LogRequest;
import com.example.adservice.repository.AdRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdServiceTest {

    @InjectMocks
    private AdService adService;
    @Mock
    private AdRepository adRepository;

    @Mock
    private AdConverter adConverter;

    @Mock
    private LogProducer logProducer;

    @Mock
    private AdActivationProducer adActivationProducer;

    @Mock
    private SubscriptionService subscriptionService;

    private static final Long USER_ID=1L;
    private static final Long AD_ID=1L;



    @Test
    void testSave_successfully() {
        SubscriptionResponse subscriptionResponse = SubscriptionResponse.builder()
                .id(1L)
                .adLimit(10)
                .build();
        FlatRequest flatRequest = new FlatRequest();
        flatRequest.setDistrict("Test");
        flatRequest.setProvince("Test");
        Ad ad = new FlatFactory().createAd(flatRequest, USER_ID);
        when(adRepository.countByUserId(USER_ID)).thenReturn(5);
        when(subscriptionService.findCurrentSubscription(USER_ID)).thenReturn(subscriptionResponse);
        when(adRepository.save(any(Ad.class))).thenReturn(ad);
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        adService.save(flatRequest, USER_ID);

        verify(adRepository, times(1)).save(any(Ad.class));
        verify(adActivationProducer, times(1)).sendAdActivation(any(AdActivationDto.class));
    }

    @Test
    void testSave_shouldThrowExhaustedAdLimitException() {
        SubscriptionResponse subscriptionResponse = SubscriptionResponse.builder()
                .id(1L)
                .adLimit(10)
                .build();
        FlatRequest flatRequest = new FlatRequest();
        flatRequest.setDistrict("Test");
        flatRequest.setProvince("Test");
        when(adRepository.countByUserId(USER_ID)).thenReturn(11);
        when(subscriptionService.findCurrentSubscription(USER_ID)).thenReturn(subscriptionResponse); // Example
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        assertThrows(ExhaustedAdLimitException.class, () -> {
            adService.save(flatRequest, USER_ID);
        });

        verify(adRepository, never()).save(any(Ad.class));
        verify(adActivationProducer, never()).sendAdActivation(any(AdActivationDto.class));
    }

    @Test
    void testSearchAll_shouldReturnAdResponseList() {

        AdSearchRequest request = new AdSearchRequest();
        request.setPage(0);
        request.setSize(10);
        request.setSort(Sort.Direction.ASC);
        Page<Ad> ads = new PageImpl<>(List.of(new Ad()));
        when(adRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(ads);
        when(adConverter.toResponse(anyList())).thenReturn(List.of(new AdResponse()));
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        SearchResponse response = adService.searchActive(request);

        assertNotNull(response);
        assertEquals(1, response.getResponse().size());
        assertEquals(1, response.getTotalPageNumber());
    }

    @Test
    void testUpdateStatus_succesfully() {
        Ad ad = Ad.builder().status(AdStatus.PASSIVE).build();
        when(adRepository.findByIdAndUserId(AD_ID,USER_ID)).thenReturn(Optional.of(ad));
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        adService.updateStatus(AdStatus.ACTIVE, AD_ID,USER_ID);

        assertEquals(AdStatus.ACTIVE, ad.getStatus());

        verify(adRepository, times(1)).save(ad);
    }

    @Test
    void testUpdateStatus_returnThrowAdNotFoundException() {
        when(adRepository.findByIdAndUserId(AD_ID,USER_ID)).thenReturn(Optional.empty());
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        assertThrows(AdNotFoundException.class,
                ()->adService.updateStatus(AdStatus.ACTIVE, AD_ID,USER_ID)) ;

        verify(adRepository,times(0)).save(any(Ad.class));
    }

    @Test
    void testFindById_shouldReturnAdResponse() {
        Ad ad = Ad.builder().id(1L).build();
        AdResponse adResponse = AdResponse.builder().id(1L).build();
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        when(adRepository.findById(AD_ID)).thenReturn(Optional.of(ad));
        when(adConverter.toResponse(ad)).thenReturn(adResponse);

        AdResponse result = adService.findById(AD_ID);

        assertNotNull(result);
        assertEquals(ad.getId(),result.getId());
        verify(adRepository, times(1)).findById(AD_ID);
    }

    @Test
    void testFindAllByUserId_shouldReturnAdResponseList() {
        Ad ad1 = Ad.builder().id(1L).build();
        Ad ad2 = Ad.builder().id(2L).build();
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        AdResponse adResponse1 = AdResponse.builder().id(1L).build();
        AdResponse adResponse2 = AdResponse.builder().id(2L).build();

        List<Ad> ads = List.of(ad1,ad2);
        List<AdResponse> adResponses = List.of(adResponse1,adResponse2);

        when(adRepository.findAllByUserId(USER_ID)).thenReturn(ads);
        when(adConverter.toResponse(ads)).thenReturn(adResponses);

        List<AdResponse> result = adService.findAllByUserIdByStatus(USER_ID, Optional.empty());

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(adRepository, times(1)).findAllByUserId(USER_ID);
    }

    @Test
    void testUpdateAllStatusByUser_successfully() {
        Ad ad1 = Ad.builder().status(AdStatus.ACTIVE).build();
        Ad ad2 = Ad.builder().status(AdStatus.ACTIVE).build();
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        List<Ad> ads = List.of(ad1,ad2);
        when(adRepository.findAllByUserId(USER_ID)).thenReturn(ads);

        adService.updateAllStatusByUser(AdStatus.PASSIVE, USER_ID);

        verify(adRepository, times(1)).saveAll(ads);
        ads.forEach(ad -> assertEquals(AdStatus.PASSIVE, ad.getStatus()));
    }
}
