package com.patika.subscriptionservice.service;

import com.patika.subscriptionservice.client.ad.service.AdService;
import com.patika.subscriptionservice.client.ad.service.AdStatus;
import com.patika.subscriptionservice.client.user.service.UserService;
import com.patika.subscriptionservice.converter.SubscriptionConverter;
import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.exception.SubscriptionNotFoundException;
import com.patika.subscriptionservice.model.Subscription;
import com.patika.subscriptionservice.producer.log.LogProducer;
import com.patika.subscriptionservice.producer.log.LogRequest;
import com.patika.subscriptionservice.repository.SubscriptionRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private LogProducer logProducer;

    @Mock
    private SubscriptionConverter subscriptionConverter;

    @Mock
    private UserService userService;

    @Mock
    private AdService adService;
    @InjectMocks
    private SubscriptionService subscriptionService;


    private static final Long USER_ID=1L;
    @Test
    void testSave_withExistingSubscription() {
        LocalDate now = LocalDate.now();
        Subscription existingSubscription = Instancio.of(Subscription.class)
                .set(field("startDate"),now)
                .set(field("endDate"),now.plusMonths(1))
                .set(field("userId"),1L)
                .create();

        when(subscriptionRepository.findTopByUserIdOrderByEndDateDesc(USER_ID))
                .thenReturn(Optional.of(existingSubscription));
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        subscriptionService.save(USER_ID);

        verify(subscriptionRepository, times(1)).save(any(Subscription.class));
        verify(logProducer,times(1)).sendLog(any(LogRequest.class));

    }

    @Test
    void testSave_withoutExistingSubscription() {
        Long userId = 1L;

        when(subscriptionRepository.findTopByUserIdOrderByEndDateDesc(userId))
                .thenReturn(Optional.empty());
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        subscriptionService.save(userId);

        verify(subscriptionRepository, times(1)).save(any(Subscription.class));
        verify(userService, times(1)).updateRoleAsSubscribed(userId);
        verify(logProducer,times(1)).sendLog(any(LogRequest.class));

    }

    @Test
    void testFindCurrentSubscription_shouldReturnSubscriptionResponse() {
        LocalDate now = LocalDate.now();
        Subscription firstSubscription = Instancio.of(Subscription.class)
                .set(field("startDate"),now)
                .set(field("endDate"),now.plusMonths(1))
                .set(field("userId"),1L)
                .create();
        Subscription secondSubscription = Instancio.of(Subscription.class)
                .set(field("startDate"),now.plusMonths(1))
                .set(field("endDate"),now.plusMonths(2))
                .set(field("userId"),1L)
                .create();
        List<Subscription> subscriptions = List.of(firstSubscription,secondSubscription);
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();

        when(subscriptionRepository.findAllByUserId(USER_ID))
                .thenReturn(subscriptions);
        when(subscriptionConverter.toResponse(firstSubscription))
                .thenReturn(subscriptionResponse);
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        SubscriptionResponse result = subscriptionService.findCurrentSubscription(USER_ID);

        assertEquals(subscriptionResponse, result);

        verify(subscriptionConverter,times(1)).toResponse(firstSubscription);
        verify(logProducer,times(1)).sendLog(any(LogRequest.class));

    }

    @Test
    void testFindCurrentSubscription_shouldThrowSubscriptionNotFoundException() {

        List<Subscription> subscriptions = List.of();

        when(subscriptionRepository.findAllByUserId(USER_ID))
                .thenReturn(subscriptions);
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        SubscriptionNotFoundException subscriptionNotFoundException = assertThrows(SubscriptionNotFoundException.class,()-> subscriptionService.findCurrentSubscription(USER_ID));
        assertThat(subscriptionNotFoundException.getMessage()).isEqualTo("Subcription not found by userId:1");

        verifyNoInteractions(subscriptionConverter);
        verify(logProducer,times(1)).sendLog(any(LogRequest.class));

    }

    @Test
    void testCancelExpiredSubscription() {
        Long userId_1 = 1L;
        Long userId_2 = 2L;

        LocalDate now = LocalDate.now();
        Subscription expiredSubscription = new Subscription(now.minusMonths(2), now.minusMonths(1), userId_1);
        Subscription activeSubscription = new Subscription(now.minusDays(10), now.plusDays(10), userId_2);

        when(subscriptionRepository.findAll())
                .thenReturn(Arrays.asList(expiredSubscription, activeSubscription));
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        subscriptionService.cancelExpiredSubscription();

        verify(adService, times(1)).updateAllStatusById(AdStatus.PASSIVE,userId_1);
        verify(adService, times(0)).updateAllStatusById(AdStatus.PASSIVE,userId_2);
        verify(userService, times(1)).updateRoleAsInitial(userId_1);
        verify(userService, times(0)).updateRoleAsInitial(userId_2);
        verify(logProducer,times(1)).sendLog(any(LogRequest.class));

    }

    @Test
    void testFindAllByUser_shouldReturnSubscriptionResponseList(){

        Subscription subscription1 = Instancio.of(Subscription.class)
                .set(field("userId"),USER_ID)
                .create();
        Subscription subscription2 = Instancio.of(Subscription.class)
                .set(field("userId"),USER_ID)
                .create();

        List<Subscription> subscriptionList = List.of(subscription1,subscription2);

        SubscriptionResponse subscriptionResponse1 = Instancio.of(SubscriptionResponse.class)
                .set(field("userId"),USER_ID)
                .create();
        SubscriptionResponse subscriptionResponse2 = Instancio.of(SubscriptionResponse.class)
                .set(field("userId"),USER_ID)
                .create();

        List<SubscriptionResponse> subscriptionResponseList = List.of(subscriptionResponse1,subscriptionResponse2);

        when(subscriptionRepository.findAllByUserId(USER_ID)).thenReturn(subscriptionList);
        when(subscriptionConverter.toResponse(subscriptionList)).thenReturn(subscriptionResponseList);
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        List<SubscriptionResponse> result = subscriptionService.findAllByUser(USER_ID);

        assertEquals(subscriptionResponseList.size(),result.size());

        verify(logProducer,times(1)).sendLog(any(LogRequest.class));

    }
}
