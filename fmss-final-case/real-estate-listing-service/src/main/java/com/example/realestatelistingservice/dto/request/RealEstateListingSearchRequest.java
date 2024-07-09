package com.example.realestatelistingservice.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.domain.Sort;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateListingSearchRequest extends BaseSearchRequest {

    private String province;

    private String district;

    private Integer minSize;

    private Integer maxSize;

    private Integer numberOfRooms;

    private Integer numberOfLivingRooms;

    private Sort.Direction sort;

}
