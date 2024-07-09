package com.patika.subscriptionservice.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionResponse {

    private Long id;

    private int listingLimit;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long userId;
}
