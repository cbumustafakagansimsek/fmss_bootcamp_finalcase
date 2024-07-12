package com.example.realestatelistingservice.factory.impl;

import com.example.realestatelistingservice.dto.request.VillaRequest;
import com.example.realestatelistingservice.factory.RealEstateListingFactory;
import com.example.realestatelistingservice.model.HouseType;
import com.example.realestatelistingservice.model.ListingStatus;
import com.example.realestatelistingservice.model.RealEstateListing;

import java.time.LocalDate;

public class VillaFactory implements RealEstateListingFactory<VillaRequest> {

    @Override
    public RealEstateListing createListing(VillaRequest request,Long userId) {
        return RealEstateListing.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .province(request.getProvince())
                .district(request.getDistrict())
                .numberOfRooms(request.getNumberOfRooms())
                .numberOfLivingRooms(request.getNumberOfLivingRooms())
                .size(request.getSize())
                .amount(request.getAmount())
                .userId(userId)
                .numberOfFloors(request.getNumberOfFloors())
                .postedDate(LocalDate.now())
                .status(ListingStatus.IN_REVIEW)
                .houseType(HouseType.VILLA)
                .yardSize(request.getYardSize())
                .hasPool(request.getHasPool())
                .build();
    }
}
