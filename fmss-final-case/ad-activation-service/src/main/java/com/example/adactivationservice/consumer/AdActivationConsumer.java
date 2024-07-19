package com.example.adactivationservice.consumer;


import com.example.adactivationservice.client.ad.dto.AdStatus;
import com.example.adactivationservice.client.ad.service.AdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdActivationConsumer {

    private final AdService adService;
    @RabbitListener(queues = "${ad.activation.queue}")
    public void sendNotification(AdActivationDto dto) {
        adService.updateStatus(AdStatus.ACTIVE, dto.getId(), dto.getUserId());
        log.info("Ad activation queue for id :{}", dto.getId());
    }



}
