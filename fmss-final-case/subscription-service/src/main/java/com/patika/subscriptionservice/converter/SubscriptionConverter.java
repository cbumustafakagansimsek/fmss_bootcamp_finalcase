package com.patika.subscriptionservice.converter;

import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.model.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConverter {

    public SubscriptionResponse toResponse(Subscription subscription){

        return SubscriptionResponse.builder()
                .id(subscription.getId())
                .adLimit(subscription.getAdLimit())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .userId(subscription.getUserId())
                .build();
    }
}
