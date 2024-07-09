package com.example.realestatelistingservice.dto.request;

import com.example.realestatelistingservice.model.ListingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlatRequest extends RealEstateListingRequest {
    private Integer floorNumber;

}
