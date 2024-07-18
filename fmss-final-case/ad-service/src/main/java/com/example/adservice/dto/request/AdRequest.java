package com.example.adservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdRequest {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Size is mandatory")
    @Min(value = 0, message = "Size should not be less than 0")
    private Integer size;

    @NotNull(message = "Amount is mandatory")
    @Min(value = 0, message = "Amount should not be less than 0")
    private BigDecimal amount;

    @NotNull(message = "Number of rooms is mandatory")
    private Integer numberOfRooms;

    @NotNull(message = "Number of living rooms is mandatory")
    @Min(value = 0, message = "Number of living rooms should not be less than 0")
    private Integer numberOfLivingRooms;

    @NotNull(message = "Number of floors is mandatory")
    private Integer numberOfFloors;

    @NotBlank(message = "Province is mandatory")
    private String province;

    @NotBlank(message = "District is mandatory")
    private String district;
}
