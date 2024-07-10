package com.example.realestatelistingservice.dto.request;

import com.example.realestatelistingservice.model.ListingStatus;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VillaRequest extends RealEstateListingRequest {

    @Min(value = 0, message = "Yard Size should not be less than 0")
    private Integer yardSize;
    private Boolean hasPool;

}
