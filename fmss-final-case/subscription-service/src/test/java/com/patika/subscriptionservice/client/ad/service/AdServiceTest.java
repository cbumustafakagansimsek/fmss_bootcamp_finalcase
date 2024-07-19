package com.patika.subscriptionservice.client.ad.service;

import com.patika.subscriptionservice.client.ad.AdClient;
import com.patika.subscriptionservice.exception.ClientException;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdServiceTest {

    @InjectMocks
    private AdService adService;

    @Mock
    private AdClient adClient;

    private static final Long USER_ID=1L;
    @Test
    void testUpdateAllStatusById_successfully(){
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);

        when(adClient.updateAllStatusByUser(AdStatus.PASSIVE,USER_ID)).thenReturn(responseEntity);

        adService.updateAllStatusById(AdStatus.PASSIVE,USER_ID);
        verify(adClient,times(1)).updateAllStatusByUser(AdStatus.PASSIVE,USER_ID);
    }

    @Test
    void testUpdateAllStatusById_failure() {

        FeignException feignException = mock(FeignException.class);
        when(feignException.contentUTF8()).thenReturn("Error message");
        when(adClient.updateAllStatusByUser(AdStatus.PASSIVE,USER_ID)).thenThrow(feignException);

        ClientException thrown = assertThrows(ClientException.class, () ->
            adService.updateAllStatusById(AdStatus.PASSIVE,USER_ID)
        );

        assertEquals("Error message", thrown.getMessage());
        verify(adClient,times(1)).updateAllStatusByUser(AdStatus.PASSIVE,USER_ID);
    }
}
