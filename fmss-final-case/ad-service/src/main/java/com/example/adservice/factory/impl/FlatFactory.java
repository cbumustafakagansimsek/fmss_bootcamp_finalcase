package com.example.adservice.factory.impl;


import com.example.adservice.dto.request.FlatRequest;
import com.example.adservice.factory.AdFactory;
import com.example.adservice.model.Ad;
import com.example.adservice.model.HouseType;
import com.example.adservice.model.AdStatus;

import java.time.LocalDate;

public class FlatFactory implements AdFactory<FlatRequest> {
    @Override
    public Ad createAd(FlatRequest request, Long userId) {
        return Ad.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .province(request.getProvince().toLowerCase())
                .district(request.getDistrict().toLowerCase())
                .amount(request.getAmount())
                .numberOfRooms(request.getNumberOfRooms())
                .numberOfLivingRooms(request.getNumberOfLivingRooms())
                .size(request.getSize())
                .numberOfFloors(request.getNumberOfFloors())
                .postedDate(LocalDate.now())
                .status(AdStatus.IN_REVIEW)
                .houseType(HouseType.FLAT)
                .userId(userId)
                .floorNumber(request.getFloorNumber())
                .build();
    }
}
