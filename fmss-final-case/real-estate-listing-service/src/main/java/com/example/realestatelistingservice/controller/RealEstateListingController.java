package com.example.realestatelistingservice.controller;


import com.example.realestatelistingservice.dto.request.DetachedHouseRequest;
import com.example.realestatelistingservice.dto.request.FlatRequest;
import com.example.realestatelistingservice.dto.request.RealEstateListingSearchRequest;
import com.example.realestatelistingservice.dto.request.VillaRequest;
import com.example.realestatelistingservice.dto.response.RealEstateListingResponse;
import com.example.realestatelistingservice.dto.response.SearchResponse;
import com.example.realestatelistingservice.model.ListingStatus;
import com.example.realestatelistingservice.model.RealEstateListing;
import com.example.realestatelistingservice.repository.spesification.RealEstateListingSpesification;
import com.example.realestatelistingservice.service.RealEstateListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/listing")
@RequiredArgsConstructor
@Slf4j
public class RealEstateListingController {

    private final RealEstateListingService realEstateListingService;

    @PostMapping("/secure/flat")
    public ResponseEntity<Void> saveFlat(@RequestHeader("x-auth-user-id") Long userId,
                                         @Valid @RequestBody FlatRequest request){
        log.info("REST Request to save real estate listing as flat: {}", request);
        realEstateListingService.save(request,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/secure/villa")
    public ResponseEntity<Void> saveVilla(@RequestHeader("x-auth-user-id") Long userId,
                                          @Valid @RequestBody VillaRequest request){
        log.info("REST Request to save real estate listing as villa: {}", request);

        realEstateListingService.save(request,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/secure/detached-house")
    public ResponseEntity<Void> saveDetachedHouse(@RequestHeader("x-auth-user-id") Long userId,
                                                  @Valid @RequestBody DetachedHouseRequest request){

        log.info("REST Request to save real estate listing as detachen house: {}", request);

        realEstateListingService.save(request,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateStatus(@RequestParam ListingStatus status,
                                             @RequestParam Long id){
        log.info("REST Request to update status as {} by id:{}",status,id);

        realEstateListingService.updateStatus(status,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<SearchResponse> searchAll(RealEstateListingSearchRequest request){
        log.info("REST Request to search all real estate listing");

        return new ResponseEntity<>(realEstateListingService.searchAll(request),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RealEstateListingResponse> findById(@PathVariable Long id){
        log.info("REST Request to find by id {}",id);

        return new ResponseEntity<>(realEstateListingService.findById(id),HttpStatus.OK);
    }
}
