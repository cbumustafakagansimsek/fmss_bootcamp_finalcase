package com.example.adactivationservice.consumer;

import com.example.adactivationservice.client.ad.dto.AdStatus;
import com.example.adactivationservice.client.ad.service.AdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdActivationConsumerTest {

    @InjectMocks
    private AdActivationConsumer adActivationConsumer;

    @Mock
    private AdService adService;

    @Test
    void testUpdateStatus_succesfully(){
        Long userId = 1L;
        Long id = 1L;

        AdActivationDto dto = new AdActivationDto(id,userId);

        doNothing().when(adService).updateStatus(AdStatus.ACTIVE,id,userId);

        adActivationConsumer.sendNotification(dto);

        verify(adService,times(1)).updateStatus(AdStatus.ACTIVE,id,userId);

    }
}
