package com.patika.realestatelistingactivationservice.consumer;


import com.patika.realestatelistingactivationservice.client.real_estate_listing.dto.ListingStatus;
import com.patika.realestatelistingactivationservice.client.real_estate_listing.service.RealEstateListingService;
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
    public void sendNotification(Long id) {
        subscriptionService.updateStatus(ListingStatus.ACTIVE, id);
        log.info("Listing activation queue for id :{}", id);
    }



}
