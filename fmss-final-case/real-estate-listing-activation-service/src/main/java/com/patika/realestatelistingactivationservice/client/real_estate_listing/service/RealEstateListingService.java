package com.patika.realestatelistingactivationservice.client.real_estate_listing.service;

import com.patika.realestatelistingactivationservice.client.real_estate_listing.RealEstateListingClient;
import com.patika.realestatelistingactivationservice.client.real_estate_listing.dto.ListingStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RealEstateListingService {
    private final RealEstateListingClient realEstateListingClient;

    public void updateStatus(ListingStatus status,Long id){
        log.info("request to update status for status:{} by id:{}",status,id);
        realEstateListingClient.updateStatus(status, id);
    }
}
