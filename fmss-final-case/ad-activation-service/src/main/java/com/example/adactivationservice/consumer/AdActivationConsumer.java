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

    private final AdService subscriptionService;
    @RabbitListener(queues = "${ad.activation.queue}")
    public void sendNotification(Long id) {
        subscriptionService.updateStatus(AdStatus.ACTIVE, id);
        log.info("Ad activation queue for id :{}", id);
    }



}
