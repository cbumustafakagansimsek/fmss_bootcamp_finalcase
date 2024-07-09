package com.example.realestatelistingservice.factory.impl;

import com.example.realestatelistingservice.dto.request.DetachedHouseRequest;
import com.example.realestatelistingservice.dto.request.FlatRequest;
import com.example.realestatelistingservice.factory.RealEstateListingFactory;
import com.example.realestatelistingservice.model.HouseType;
import com.example.realestatelistingservice.model.ListingStatus;
import com.example.realestatelistingservice.model.RealEstateListing;
import com.example.realestatelistingservice.repository.RealEstateListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

@RequiredArgsConstructor
public class DetachedHouseFactory implements RealEstateListingFactory<DetachedHouseRequest> {

    @Override
    public RealEstateListing createListing(DetachedHouseRequest request ) {
        return RealEstateListing.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .province(request.getProvince())
                .district(request.getDistrict())
                .numberOfRooms(request.getNumberOfRooms())
                .numberOfLivingRooms(request.getNumberOfLivingRooms())
                .amount(request.getAmount())
                .size(request.getSize())
                .userId(request.getUserId())
                .postedDate(LocalDate.now())
                .yardSize(request.getYardSize())
                .status(ListingStatus.IN_REVIEW)
                .houseType(HouseType.DETACHED_HOUSE)
                .build();
    }
}
