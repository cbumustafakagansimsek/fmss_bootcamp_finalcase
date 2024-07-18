package com.example.adservice.factory.impl;



import com.example.adservice.dto.request.VillaRequest;
import com.example.adservice.factory.AdFactory;
import com.example.adservice.model.Ad;
import com.example.adservice.model.HouseType;
import com.example.adservice.model.AdStatus;

import java.time.LocalDate;

public class VillaFactory implements AdFactory<VillaRequest> {

    @Override
    public Ad createAd(VillaRequest request, Long userId) {
        return Ad.builder()
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
                .status(AdStatus.IN_REVIEW)
                .houseType(HouseType.VILLA)
                .yardSize(request.getYardSize())
                .hasPool(request.getHasPool())
                .build();
    }
}
