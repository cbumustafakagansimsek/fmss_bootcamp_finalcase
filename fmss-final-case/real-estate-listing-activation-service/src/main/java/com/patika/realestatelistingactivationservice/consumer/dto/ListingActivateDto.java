package com.patika.realestatelistingactivationservice.consumer.dto;

import com.patika.realestatelistingactivationservice.client.real_estate_listing.dto.ListingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListingActivateDto {
    private ListingStatus status;

    private Long id;
}
