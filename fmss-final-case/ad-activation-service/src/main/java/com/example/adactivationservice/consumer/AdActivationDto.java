package com.example.adactivationservice.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdActivationDto {
    private Long id;

    private Long userId;
}
