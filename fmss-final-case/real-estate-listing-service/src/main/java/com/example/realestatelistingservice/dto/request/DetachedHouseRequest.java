package com.example.realestatelistingservice.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetachedHouseRequest extends RealEstateListingRequest{
    private Integer yardSize;

}
