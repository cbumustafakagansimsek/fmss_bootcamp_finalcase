package com.patika.subscriptionservice.repository;

import com.patika.subscriptionservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    Optional<Subscription> findTopByUserIdOrderByEndDateDesc(Long id);

    List<Subscription> findAllByUserId(Long userId);

}
