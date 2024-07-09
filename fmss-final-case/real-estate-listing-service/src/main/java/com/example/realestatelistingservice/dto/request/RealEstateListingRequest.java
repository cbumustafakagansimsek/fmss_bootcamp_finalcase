package com.example.realestatelistingservice.dto.request;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RealEstateListingRequest {
    private String title;
    private String description;
    private Integer size;
    private Long userId;
    private BigDecimal amount;
    private Integer numberOfRooms;
    private Integer numberOfLivingRooms;
    private Integer ageOfBuilding;
    private Boolean inBuildingComplex;
    private String province;
    private String district;
}
