package com.patika.realestatelistingactivationservice.client.real_estate_listing;

import com.patika.realestatelistingactivationservice.client.real_estate_listing.dto.ListingStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service",path = "/api/v1/user")
public interface RealEstateListingClient {

    @PutMapping
     ResponseEntity<Void> updateStatus(@RequestParam ListingStatus status, @RequestParam Long id);

}
