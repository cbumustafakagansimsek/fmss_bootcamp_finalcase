package com.example.adservice.dto.request;

import lombok.*;
import org.springframework.data.domain.Sort;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdSearchRequest extends BaseSearchRequest {

    private String province;

    private String district;

    private Integer minSize;

    private Integer maxSize;

    private Integer numberOfRooms;

    private Integer numberOfLivingRooms;

    private Sort.Direction sort;

}
