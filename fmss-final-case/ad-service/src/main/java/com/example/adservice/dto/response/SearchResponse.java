package com.example.adservice.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
    private List<AdResponse> response;

    private int totalPageNumber ;
}
