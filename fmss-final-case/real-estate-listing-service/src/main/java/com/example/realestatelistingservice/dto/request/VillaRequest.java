package com.example.realestatelistingservice.dto.request;

import com.example.realestatelistingservice.model.ListingStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VillaRequest extends RealEstateListingRequest {

    private Integer yardSize;
    private Boolean hasPool;

}
