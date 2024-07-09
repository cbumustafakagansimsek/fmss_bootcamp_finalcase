package com.patika.realestatelistingactivationservice.consumer;


import com.patika.realestatelistingactivationservice.client.real_estate_listing.service.RealEstateListingService;
import com.patika.realestatelistingactivationservice.consumer.dto.ListingActivateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ListingActivationConsumer {

    private final RealEstateListingService subscriptionService;
    @RabbitListener(queues = "${listing.activation.queue}")
    public void sendNotification(ListingActivateDto dto) {
        subscriptionService.updateStatus(dto.getStatus(), dto.getId());
        log.info("Listing activation queue for id :{}", dto.getId());

    }



}
