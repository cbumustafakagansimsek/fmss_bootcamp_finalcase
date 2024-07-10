package com.example.realestatelistingservice.dto.request;

import com.example.realestatelistingservice.model.ListingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FlatRequest extends RealEstateListingRequest {

    @Min(value = 0, message = "Floor Number should not be less than 0")
    private Integer floorNumber;

}
