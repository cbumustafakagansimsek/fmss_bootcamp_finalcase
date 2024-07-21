package com.patika.paymentservice.producer.subscription.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionQueueDto {
    private Long UserId;

    private Integer productAmount;

}
