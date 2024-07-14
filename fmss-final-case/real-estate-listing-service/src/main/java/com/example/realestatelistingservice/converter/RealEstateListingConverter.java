package com.example.realestatelistingservice.converter;

import com.example.realestatelistingservice.dto.request.DetachedHouseRequest;
import com.example.realestatelistingservice.dto.request.FlatRequest;
import com.example.realestatelistingservice.dto.request.RealEstateListingRequest;
import com.example.realestatelistingservice.dto.request.VillaRequest;
import com.example.realestatelistingservice.dto.response.RealEstateListingResponse;
import com.example.realestatelistingservice.model.HouseType;
import com.example.realestatelistingservice.model.ListingStatus;
import com.example.realestatelistingservice.model.RealEstateListing;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RealEstateListingConverter {

    public RealEstateListingResponse toResponse(RealEstateListing listing){
        return RealEstateListingResponse.builder()
                .id(listing.getId())
                .title(listing.getTitle())
                .description(listing.getDescription())
                .houseType(listing.getHouseType())
                .amount(listing.getAmount())
                .province(listing.getProvince())
                .district(listing.getDistrict())
                .size(listing.getSize())
                .userId(listing.getUserId())
                .numberOfRooms(listing.getNumberOfRooms())
                .numberOfLivingRooms(listing.getNumberOfLivingRooms())
                .postedDate(listing.getPostedDate())
                .floorNumber(listing.getFloorNumber())
                .yardSize(listing.getYardSize())
                .hasPool(listing.getHasPool())
                .status(listing.getStatus())
                .build();
    }

    public List<RealEstateListingResponse> toResponse(List<RealEstateListing> listing){
        return listing.stream()
                .map(this::toResponse)
                .toList();
    }


}
