package com.example.adservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "house_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    @Column(name = "amount",nullable = false)
    private BigDecimal amount;
    @Column(name = "province",nullable = false)
    private String province;

    @Column(name = "district",nullable = false)
    private String district;

    @Column(name = "size",nullable = false)
    private Integer size;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name="number_of_rooms",nullable = false)
    private Integer numberOfRooms;

    @Column(name="number_of_living_rooms",nullable = false)
    private Integer numberOfLivingRooms;

    @Column(name = "posted_date",nullable = false)
    private LocalDate postedDate;

    @Column(name = "number_of_floors",nullable = false)
    private Integer numberOfFloors;
    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "yard_size")
    private Integer yardSize;

    @Column(name = "has_pool")
    private Boolean hasPool;
    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private AdStatus status;




}
