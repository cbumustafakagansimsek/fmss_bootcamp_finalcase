package com.example.realestatelistingservice.service;

import com.example.realestatelistingservice.client.subscription.service.SubscriptionService;
import com.example.realestatelistingservice.converter.RealEstateListingConverter;
import com.example.realestatelistingservice.dto.request.*;
import com.example.realestatelistingservice.dto.response.RealEstateListingResponse;
import com.example.realestatelistingservice.factory.impl.DetachedHouseFactory;
import com.example.realestatelistingservice.factory.impl.FlatFactory;
import com.example.realestatelistingservice.factory.impl.VillaFactory;
import com.example.realestatelistingservice.model.ListingStatus;
import com.example.realestatelistingservice.model.RealEstateListing;
import com.example.realestatelistingservice.repository.RealEstateListingRepository;
import com.example.realestatelistingservice.repository.spesification.RealEstateListingSpesification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RealEstateListingService {
    private final RealEstateListingRepository realEstateListingRepository;

    private final RealEstateListingConverter realEstateListingConverter;

    private final SubscriptionService subscriptionService;

    public <T> void save(T c ){
        RealEstateListing listing;
        if (c.getClass() == FlatRequest.class ){
            listing = new FlatFactory().createListing((FlatRequest) c);
        } else if (c.getClass() == VillaRequest.class) {
            listing = new VillaFactory().createListing((VillaRequest) c);
        }else if (c.getClass() == DetachedHouseRequest.class) {
            listing = new DetachedHouseFactory().createListing((DetachedHouseRequest) c);
        }else {
            throw new RuntimeException("House type not found");
        }
        System.out.println(realEstateListingRepository.countByUserId(listing.getUserId()));
        if (realEstateListingRepository.countByUserId(listing.getUserId())>=subscriptionService.findCurrentSubscription(listing.getUserId()).getListingLimit()){
        throw new RuntimeException("hakkınız kalmadı");
        }
        realEstateListingRepository.save(listing);

    }

    public List<RealEstateListingResponse> searchAll(RealEstateListingSearchRequest request){
            Specification<RealEstateListing> productSpecification = RealEstateListingSpesification.initRealEstateListingSpecification(request);

            PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), Sort.by(request.getSort(), "amount"));

            Page<RealEstateListing> listings = realEstateListingRepository.findAll(productSpecification, pageRequest);

            log.info("searchAll listing size:{}", listings.getSize());

            return realEstateListingConverter.toResponse(listings.stream().toList());

    }

    public void updateStatus(ListingStatus status,Long id){
        RealEstateListing realEstateListing = realEstateListingRepository.findById(id).orElseThrow();
        realEstateListing.setStatus(status);

        realEstateListingRepository.save(realEstateListing);
    }

    
}
