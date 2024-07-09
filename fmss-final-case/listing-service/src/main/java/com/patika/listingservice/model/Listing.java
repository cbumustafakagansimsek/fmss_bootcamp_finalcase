package com.patika.listingservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "listing")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "posted_date",nullable = false)
    private LocalDate postedDate;

    @Column(name = "expiration_date",nullable = false)
    private LocalDate expirationDate;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ListingStatus status;


}
