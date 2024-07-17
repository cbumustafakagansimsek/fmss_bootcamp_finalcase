package com.patika.subscriptionservice.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MultipleSubscriptionRequest {
    private Long UserId;

    private Integer productAmount;

}