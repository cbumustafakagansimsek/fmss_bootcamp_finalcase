package com.example.realestatelistingservice.factory.impl;

import com.example.realestatelistingservice.dto.request.FlatRequest;
import com.example.realestatelistingservice.factory.RealEstateListingFactory;
import com.example.realestatelistingservice.model.HouseType;
import com.example.realestatelistingservice.model.ListingStatus;
import com.example.realestatelistingservice.model.RealEstateListing;

import java.time.LocalDate;

public class FlatFactory implements RealEstateListingFactory<FlatRequest> {
    @Override
    public RealEstateListing createListing(FlatRequest request) {
        return RealEstateListing.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .province(request.getProvince())
                .district(request.getDistrict())
                .amount(request.getAmount())
                .numberOfRooms(request.getNumberOfRooms())
                .numberOfLivingRooms(request.getNumberOfLivingRooms())
                .size(request.getSize())
                .postedDate(LocalDate.now())
                .status(ListingStatus.IN_REVIEW)
                .houseType(HouseType.FLAT)
                .userId(request.getUserId())
                .floorNumber(request.getFloorNumber())
                .build();
    }
}
