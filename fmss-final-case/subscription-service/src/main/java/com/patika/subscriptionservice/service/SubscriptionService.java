package com.patika.subscriptionservice.service;

import com.patika.subscriptionservice.client.user.response.UserResponse;
import com.patika.subscriptionservice.client.user.service.UserService;
import com.patika.subscriptionservice.converter.SubscriptionConverter;
import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.exception.SubscriptionNotFoundException;
import com.patika.subscriptionservice.model.Subscription;
import com.patika.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionConverter subscriptionConverter;
    private final UserService userService;

    public void save(Long id){
        UserResponse userResponse = userService.findById(id);
        LocalDate now = LocalDate.now();

        Optional<Subscription> lastProduct = subscriptionRepository.findTopByUserIdOrderByEndDateDesc(id);
        if(lastProduct.isPresent() && !lastProduct.get().getEndDate().isBefore(now)){
            //If there is a subscription
            subscriptionRepository.save(new Subscription(lastProduct.get().getEndDate().plusDays(1),
                                        lastProduct.get().getEndDate().plusMonths(1),
                                        id));

        }else {
            //If there is a subscription
            subscriptionRepository.save(new Subscription(now,now.plusMonths(1),id));

            //change user role as a subscripted
            userService.updateRoleAsSubscribed(id);
        }

    }

    public SubscriptionResponse findCurrentSubscription(Long userId){
        Subscription subscription = subscriptionRepository.findAllByUserId(userId).stream()
                .collect(Collectors.minBy(Comparator.comparing(Subscription::getEndDate)))
                .orElseThrow(()-> new SubscriptionNotFoundException("Subcription not found by userId"+userId));

        return subscriptionConverter.toResponse(subscription);

    }

    @Scheduled(cron = "0 0 0 * * ?")
    private void cancelExpiredProduct() {
        LocalDate now = LocalDate.now();

        List<Subscription> subscriptions = subscriptionRepository.findAll().stream()
                .collect(Collectors.groupingBy(Subscription::getUserId,Collectors.maxBy(Comparator.comparing(Subscription::getEndDate))))
                .values()
                .stream()
                .map(Optional::get)
                .toList();
        for (Subscription subscription : subscriptions) {
            if (subscription.getEndDate().isBefore(now)) {
                userService.updateRoleAsInitial(subscription.getUserId());
            }
        }
    }

}
