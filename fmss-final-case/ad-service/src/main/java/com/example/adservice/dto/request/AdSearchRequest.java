package com.example.adservice.dto.request;

import com.example.adservice.model.AdStatus;
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

    private String houseType;

    private Integer maxSize;

    private Integer numberOfRooms;

    private Integer numberOfLivingRooms;

    private Sort.Direction sort;

}
