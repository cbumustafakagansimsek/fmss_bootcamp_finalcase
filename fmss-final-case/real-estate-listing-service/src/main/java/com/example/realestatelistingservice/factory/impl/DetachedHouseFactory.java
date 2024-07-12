package com.example.realestatelistingservice.factory.impl;

import com.example.realestatelistingservice.dto.request.DetachedHouseRequest;
import com.example.realestatelistingservice.factory.RealEstateListingFactory;
import com.example.realestatelistingservice.model.HouseType;
import com.example.realestatelistingservice.model.ListingStatus;
import com.example.realestatelistingservice.model.RealEstateListing;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class DetachedHouseFactory implements RealEstateListingFactory<DetachedHouseRequest> {

    @Override
    public RealEstateListing createListing(DetachedHouseRequest request ,Long userId) {
        return RealEstateListing.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .province(request.getProvince())
                .district(request.getDistrict())
                .numberOfRooms(request.getNumberOfRooms())
                .numberOfLivingRooms(request.getNumberOfLivingRooms())
                .amount(request.getAmount())
                .numberOfFloors(request.getNumberOfFloors())
                .size(request.getSize())
                .userId(userId)
                .postedDate(LocalDate.now())
                .yardSize(request.getYardSize())
                .status(ListingStatus.IN_REVIEW)
                .houseType(HouseType.DETACHED_HOUSE)
                .build();
    }
}
