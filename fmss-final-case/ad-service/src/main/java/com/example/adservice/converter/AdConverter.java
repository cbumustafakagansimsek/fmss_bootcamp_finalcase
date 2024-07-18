package com.example.adservice.converter;


import com.example.adservice.dto.response.AdResponse;
import com.example.adservice.model.Ad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdConverter {

    public AdResponse toResponse(Ad ad){
        return AdResponse.builder()
                .id(ad.getId())
                .title(ad.getTitle())
                .description(ad.getDescription())
                .houseType(ad.getHouseType())
                .amount(ad.getAmount())
                .province(ad.getProvince())
                .district(ad.getDistrict())
                .size(ad.getSize())
                .userId(ad.getUserId())
                .numberOfRooms(ad.getNumberOfRooms())
                .numberOfLivingRooms(ad.getNumberOfLivingRooms())
                .postedDate(ad.getPostedDate())
                .floorNumber(ad.getFloorNumber())
                .yardSize(ad.getYardSize())
                .hasPool(ad.getHasPool())
                .status(ad.getStatus())
                .build();
    }

    public List<AdResponse> toResponse(List<Ad> ad){
        return ad.stream()
                .map(this::toResponse)
                .toList();
    }


}
