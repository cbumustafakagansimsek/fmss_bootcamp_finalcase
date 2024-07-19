package com.example.adservice.producer;

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
