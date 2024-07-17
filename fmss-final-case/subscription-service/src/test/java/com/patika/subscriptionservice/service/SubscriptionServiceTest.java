package com.patika.subscriptionservice.service;

import com.patika.subscriptionservice.client.user.service.UserService;
import com.patika.subscriptionservice.converter.SubscriptionConverter;
import com.patika.subscriptionservice.dto.response.SubscriptionResponse;
import com.patika.subscriptionservice.exception.SubscriptionNotFoundException;
import com.patika.subscriptionservice.model.Subscription;
import com.patika.subscriptionservice.repository.SubscriptionRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    private SubscriptionConverter subscriptionConverter;

    @Mock
    private UserService userService;

    @InjectMocks
    private SubscriptionService subscriptionService;



    @Test
    void testSave_WithExistingSubscription() {
        Long userId = 1L;
        LocalDate now = LocalDate.now();
        Subscription existingSubscription = Instancio.of(Subscription.class)
                .set(field("startDate"),now)
                .set(field("endDate"),now.plusMonths(1))
                .set(field("userId"),1L)
                .create();

        when(subscriptionRepository.findTopByUserIdOrderByEndDateDesc(userId))
                .thenReturn(Optional.of(existingSubscription));

        subscriptionService.save(userId);

        verify(subscriptionRepository, times(1)).save(Mockito.any(Subscription.class));
    }

    @Test
    void testSave_WithoutExistingSubscription() {
        Long userId = 1L;

        when(subscriptionRepository.findTopByUserIdOrderByEndDateDesc(userId))
                .thenReturn(Optional.empty());

        subscriptionService.save(userId);

        verify(subscriptionRepository, times(1)).save(Mockito.any(Subscription.class));
        verify(userService, times(1)).updateRoleAsSubscribed(userId);
    }

    @Test
    void testFindCurrentSubscription() {
        Long userId = 1L;
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

        when(subscriptionRepository.findAllByUserId(userId))
                .thenReturn(subscriptions);
        when(subscriptionConverter.toResponse(firstSubscription))
                .thenReturn(subscriptionResponse);

        SubscriptionResponse result = subscriptionService.findCurrentSubscription(userId);

        verify(subscriptionConverter,times(1)).toResponse(firstSubscription);
        assertEquals(subscriptionResponse, result);
    }

    @Test
    void testFindCurrentSubscription_shouldThrowSubscriptionNotFoundException() {
        Long userId = 1L;

        List<Subscription> subscriptions = List.of();

        when(subscriptionRepository.findAllByUserId(userId))
                .thenReturn(subscriptions);

        SubscriptionNotFoundException subscriptionNotFoundException = assertThrows(SubscriptionNotFoundException.class,()-> subscriptionService.findCurrentSubscription(userId));
        assertThat(subscriptionNotFoundException.getMessage()).isEqualTo("Subcription not found by userId:1");

        verifyNoInteractions(subscriptionConverter);

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

        subscriptionService.cancelExpiredSubscription();

        verify(userService, times(1)).updateRoleAsInitial(userId_1);
        verify(userService, times(0)).updateRoleAsInitial(userId_2);
    }
}
