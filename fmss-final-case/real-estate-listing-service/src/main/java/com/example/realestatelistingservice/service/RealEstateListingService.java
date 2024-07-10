package com.example.realestatelistingservice.service;

import com.example.realestatelistingservice.client.subscription.service.SubscriptionService;
import com.example.realestatelistingservice.converter.RealEstateListingConverter;
import com.example.realestatelistingservice.dto.request.*;
import com.example.realestatelistingservice.dto.response.RealEstateListingResponse;
import com.example.realestatelistingservice.exception.ExhaustedListingLimitException;
import com.example.realestatelistingservice.exception.HouseTypeIsInvalidException;
import com.example.realestatelistingservice.exception.RealEstateListingNotFoundException;
import com.example.realestatelistingservice.factory.impl.DetachedHouseFactory;
import com.example.realestatelistingservice.factory.impl.FlatFactory;
import com.example.realestatelistingservice.factory.impl.VillaFactory;
import com.example.realestatelistingservice.model.ListingStatus;
import com.example.realestatelistingservice.model.RealEstateListing;
import com.example.realestatelistingservice.producer.ListingActivationProducer;
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

    private final ListingActivationProducer listingActivationProducer;
    private final SubscriptionService subscriptionService;

    public <T> void save(T c ){
        log.info("Request to save real estate listing");
        RealEstateListing listing;
        if (c.getClass() == FlatRequest.class ){
            listing = new FlatFactory().createListing((FlatRequest) c);
        } else if (c.getClass() == VillaRequest.class) {
            listing = new VillaFactory().createListing((VillaRequest) c);
        }else if (c.getClass() == DetachedHouseRequest.class) {
            listing = new DetachedHouseFactory().createListing((DetachedHouseRequest) c);
        }else {
            throw new HouseTypeIsInvalidException("Request class is invalid ");
        }

        if (realEstateListingRepository.countByUserId(listing.getUserId())>=subscriptionService.findCurrentSubscription(listing.getUserId()).getListingLimit()){
        throw new ExhaustedListingLimitException("Listing posting limit has been exhausted.");
        }

        RealEstateListing realEstateListing = realEstateListingRepository.save(listing);

        listingActivationProducer.sendListingActivation(realEstateListing.getId());
    }

    public List<RealEstateListingResponse> searchAll(RealEstateListingSearchRequest request){

        log.info("Request to search all real estate listing");

        Specification<RealEstateListing> productSpecification = RealEstateListingSpesification.initRealEstateListingSpecification(request);

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), Sort.by(request.getSort(), "amount"));

        Page<RealEstateListing> listings = realEstateListingRepository.findAll(productSpecification, pageRequest);

        return realEstateListingConverter.toResponse(listings.stream().toList());

    }

    public void updateStatus(ListingStatus status,Long id){
        log.info("Request to update status as {} by id:{}",status,id);

        RealEstateListing realEstateListing = realEstateListingRepository.findById(id)
                .orElseThrow(()->new RealEstateListingNotFoundException("Real estate listing not found by id:"+id));

        realEstateListing.setStatus(status);

        realEstateListingRepository.save(realEstateListing);
    }

    
}
