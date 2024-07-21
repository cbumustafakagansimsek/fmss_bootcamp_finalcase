package com.patika.subscriptionservice.service;

import com.patika.subscriptionservice.client.ad.service.AdService;
import com.patika.subscriptionservice.client.ad.service.AdStatus;
import com.patika.subscriptionservice.client.user.service.UserService;
import com.patika.subscriptionservice.converter.SubscriptionConverter;
import com.patika.subscriptionservice.dto.request.MultipleSubscriptionRequest;
import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.exception.SubscriptionNotFoundException;
import com.patika.subscriptionservice.model.Subscription;
import com.patika.subscriptionservice.producer.log.LogProducer;
import com.patika.subscriptionservice.producer.log.LogRequest;
import com.patika.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionConverter subscriptionConverter;
    private final UserService userService;

    private final LogProducer logProducer;
    private final AdService adService;

    public void save(Long id){
        log.info("Request to save subscription by id:{}",id);
        logProducer.sendLog(new LogRequest("[subscription-service]",
                Level.INFO,
                "subscriptionservice",
                "Request to save subscription by id: "+id,
                new Date()));

        //UserResponse userResponse = userService.findById(id);
        LocalDate now = LocalDate.now();

        Optional<Subscription> lastProduct = subscriptionRepository.findTopByUserIdOrderByEndDateDesc(id);
        if(lastProduct.isPresent() && !lastProduct.get().getEndDate().isBefore(now)){
            //If there is a subscription
            subscriptionRepository.save(new Subscription(lastProduct.get().getEndDate().plusDays(1),
                                        lastProduct.get().getEndDate().plusMonths(1),
                                        id));

        }else {
            //If there is no subscription
            subscriptionRepository.save(new Subscription(now,now.plusMonths(1),id));

            //Change user role as a subscripted
            userService.updateRoleAsSubscribed(id);
        }

    }

    public void saveMultiple(MultipleSubscriptionRequest request){
        log.info("Request to save multiple subscriptions :{}",request.getProductAmount());
        logProducer.sendLog(new LogRequest("[subscription-service]",
                Level.INFO,
                "subscriptionservice",
                "Request to save multiple subscriptions :: "+request.getProductAmount(),
                new Date()));

        for (int i=0;i< request.getProductAmount();i++){
            save(request.getUserId());
        }
    }

    public List<SubscriptionResponse> findAllByUser(Long userId){
        log.info("Request to find all subscriptions by user id:{}",userId);
        logProducer.sendLog(new LogRequest("[subscription-service]",
                Level.INFO,
                "subscriptionservice",
                "Request to find all subscriptions by user id: "+userId,
                new Date()));

        return subscriptionConverter.toResponse(subscriptionRepository.findAllByUserId(userId));
    }

    public SubscriptionResponse findCurrentSubscription(Long userId){
        log.info("Request to find current subscription by id:{}",userId);
        logProducer.sendLog(new LogRequest("[subscription-service]",
                Level.INFO,
                "subscriptionservice",
                "Request to find current subscription by id: "+userId,
                new Date()));

        Subscription subscription = subscriptionRepository.findAllByUserId(userId).stream()
                .collect(Collectors.minBy(Comparator.comparing(Subscription::getEndDate)))
                .orElseThrow(()-> new SubscriptionNotFoundException("Subcription not found by userId:"+userId));

        return subscriptionConverter.toResponse(subscription);

    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void cancelExpiredSubscription() {
        log.info("Cancellation of scheduled Expired subscriptions");
        logProducer.sendLog(new LogRequest("[subscription-service]",
                Level.INFO,
                "subscriptionservice",
                "Cancellation of scheduled Expired subscriptions",
                new Date()));

        //List of users' latest subscription packages
        List<Subscription> subscriptions = subscriptionRepository.findAll().stream()
                .collect(Collectors.groupingBy(Subscription::getUserId,Collectors.maxBy(Comparator.comparing(Subscription::getEndDate))))
                .values()
                .stream()
                .map(Optional::get)
                .toList();

        for (Subscription subscription : subscriptions) {
            if (subscription.getEndDate().isBefore(LocalDate.now())) {
                log.info("Cancellation subscriotion for id:{}",subscription.getUserId());
                adService.updateAllStatusById(AdStatus.PASSIVE, subscription.getUserId());
                userService.updateRoleAsInitial(subscription.getUserId());
            }
        }
    }

}
