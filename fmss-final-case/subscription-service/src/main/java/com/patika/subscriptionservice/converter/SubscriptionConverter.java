package com.patika.subscriptionservice.converter;

import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.model.Subscription;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<SubscriptionResponse> toResponse(List<Subscription> subscriptions){

        return subscriptions.stream()
                .map(this::toResponse)
                .toList();
    }
}
