package com.example.realestatelistingservice.factory;

import com.example.realestatelistingservice.dto.request.RealEstateListingRequest;
import com.example.realestatelistingservice.model.RealEstateListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateListingFactory<T> {

    RealEstateListing createListing(T object,Long userId);
}
