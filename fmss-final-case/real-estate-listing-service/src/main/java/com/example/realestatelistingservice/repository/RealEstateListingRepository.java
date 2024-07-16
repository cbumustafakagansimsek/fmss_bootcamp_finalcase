package com.example.realestatelistingservice.repository;

import com.example.realestatelistingservice.model.ListingStatus;
import com.example.realestatelistingservice.model.RealEstateListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealEstateListingRepository extends JpaRepository<RealEstateListing, Long>, JpaSpecificationExecutor<RealEstateListing> {
    int countByUserId(Long userId);

    List<RealEstateListing> findAllByUserId(Long userId);
    List<RealEstateListing> findAllByUserIdAndStatus(Long userId, ListingStatus status);

}
