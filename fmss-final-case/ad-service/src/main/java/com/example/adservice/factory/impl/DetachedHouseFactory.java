package com.example.adservice.factory.impl;


import com.example.adservice.dto.request.DetachedHouseRequest;
import com.example.adservice.factory.AdFactory;
import com.example.adservice.model.Ad;
import com.example.adservice.model.HouseType;
import com.example.adservice.model.AdStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class DetachedHouseFactory implements AdFactory<DetachedHouseRequest> {

    @Override
    public Ad createAd(DetachedHouseRequest request , Long userId) {
        return Ad.builder()
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
                .status(AdStatus.IN_REVIEW)
                .houseType(HouseType.DETACHED_HOUSE)
                .build();
    }
}
