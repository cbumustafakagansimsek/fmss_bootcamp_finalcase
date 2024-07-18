package com.example.adservice.client.subscription.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionResponse {

    private Long id;

    private int adLimit;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long userId;
}
