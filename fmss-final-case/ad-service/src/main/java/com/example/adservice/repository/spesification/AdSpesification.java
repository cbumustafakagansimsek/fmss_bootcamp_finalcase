package com.example.adservice.repository.spesification;


import com.example.adservice.dto.request.AdSearchRequest;
import com.example.adservice.model.Ad;
import com.example.adservice.model.AdStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdSpesification {

    public static Specification<Ad> initRealEstateAdSpecification(AdSearchRequest request) {
        return (root, query, criteriaBuilder) -> {


            List<Predicate> predicateList = new ArrayList<>();

            if (request.getProvince() != null) {
                predicateList.add(criteriaBuilder.like(root.get("province"),"%" + request.getProvince().toLowerCase()+"%"));
            }

            if (request.getDistrict() != null) {
                predicateList.add(criteriaBuilder.like(root.get("district"),"%" + request.getDistrict().toLowerCase()+"%"));
            }

            if (request.getMinSize() != null) {
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("size"), request.getMinSize()));
            }
            if (request.getMaxSize() != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("size"), request.getMaxSize()));
            }

            if (request.getNumberOfRooms() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("numberOfRooms"), request.getNumberOfRooms()));
            }

            if (request.getNumberOfLivingRooms() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("numberOfLivingRooms"), request.getNumberOfLivingRooms()));
            }

            predicateList.add(criteriaBuilder.equal(root.get("status"), AdStatus.ACTIVE));

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
