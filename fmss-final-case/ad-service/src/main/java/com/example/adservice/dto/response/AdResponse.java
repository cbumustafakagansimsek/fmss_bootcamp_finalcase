package com.example.adservice.dto.response;


import com.example.adservice.model.HouseType;
import com.example.adservice.model.AdStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdResponse {
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

    private AdStatus status;
}
