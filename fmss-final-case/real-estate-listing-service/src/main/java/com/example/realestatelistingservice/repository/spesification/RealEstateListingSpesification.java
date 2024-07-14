package com.example.realestatelistingservice.repository.spesification;

import com.example.realestatelistingservice.dto.request.RealEstateListingSearchRequest;
import com.example.realestatelistingservice.model.RealEstateListing;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RealEstateListingSpesification {

    public static Specification<RealEstateListing> initRealEstateListingSpecification(RealEstateListingSearchRequest request) {
        return (root, query, criteriaBuilder) -> {


            List<Predicate> predicateList = new ArrayList<>();

            if (request.getProvince() != null) {
                predicateList.add(criteriaBuilder.like(root.get("province"),"%" + request.getProvince()+"%"));
            }

            if (request.getDistrict() != null) {
                predicateList.add(criteriaBuilder.like(root.get("district"),"%" + request.getDistrict()+"%"));
            }

            if (request.getMaxSize() != null && request.getMinSize() != null) {
                predicateList.add(criteriaBuilder.between(root.get("size"), request.getMinSize(), request.getMaxSize()));
            }

            if (request.getNumberOfRooms() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("numberOfRooms"), request.getNumberOfRooms()));
            }

            if (request.getNumberOfLivingRooms() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("numberOfLivingRooms"), request.getNumberOfLivingRooms()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
