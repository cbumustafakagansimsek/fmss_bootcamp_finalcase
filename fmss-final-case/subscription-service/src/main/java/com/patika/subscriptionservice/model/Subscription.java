package com.patika.subscriptionservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ad_limit",nullable = false)
    private int adLimit;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date",nullable = false)
    private LocalDate endDate;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    public Subscription(LocalDate startDate, LocalDate endDate, Long userId) {
        this.adLimit = 10;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }
}
