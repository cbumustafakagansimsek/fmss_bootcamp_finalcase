package com.example.adactivationservice.clien.ad;

import com.example.adactivationservice.client.ad.AdClient;
import com.example.adactivationservice.client.ad.dto.AdStatus;
import com.example.adactivationservice.client.ad.service.AdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AdServiceTest {

    @InjectMocks
    private AdService adService;

    @Mock
    private AdClient adClient;

    private static final Long USER_ID=1L;
    private static final Long ID=1L;

    @Test
    void testupdateStatus_successfully(){
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);


        when(adClient.updateStatus(AdStatus.PASSIVE,ID,USER_ID)).thenReturn(responseEntity);

        adService.updateStatus(AdStatus.PASSIVE,ID,USER_ID);
        verify(adClient,times(1)).updateStatus(AdStatus.PASSIVE,ID,USER_ID);
    }


}
