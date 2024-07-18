package com.example.adservice.dto.request;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VillaRequest extends AdRequest {

    @Min(value = 0, message = "Yard Size should not be less than 0")
    private Integer yardSize;
    private Boolean hasPool;

}
