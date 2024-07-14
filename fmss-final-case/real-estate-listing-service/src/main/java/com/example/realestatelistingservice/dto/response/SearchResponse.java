package com.example.realestatelistingservice.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
    private List<RealEstateListingResponse> response;

    private int totalPageNumber ;
}
