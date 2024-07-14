package com.example.realestatelistingservice.dto.response;

import com.example.realestatelistingservice.model.HouseType;
import com.example.realestatelistingservice.model.ListingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RealEstateListingResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private BigDecimal amount;

    private HouseType houseType;

    private String province;

    private String district;

    private Integer size;

    private Long userId;

    private Integer numberOfRooms;

    private Integer numberOfLivingRooms;

    private LocalDate postedDate;
    private Integer floorNumber;

    private Integer yardSize;

    private Boolean hasPool;

    private ListingStatus status;
}
