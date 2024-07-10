package com.example.realestatelistingservice.controller;


import com.example.realestatelistingservice.dto.request.DetachedHouseRequest;
import com.example.realestatelistingservice.dto.request.FlatRequest;
import com.example.realestatelistingservice.dto.request.RealEstateListingSearchRequest;
import com.example.realestatelistingservice.dto.request.VillaRequest;
import com.example.realestatelistingservice.dto.response.RealEstateListingResponse;
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

    @PostMapping("/flat")
    public ResponseEntity<Void> saveFlat(@Valid @RequestBody FlatRequest request){
        log.debug("REST Request to save real estate listing as flat: {}", request);
        realEstateListingService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/villa")
    public ResponseEntity<Void> saveVilla(@Valid @RequestBody VillaRequest request){
        realEstateListingService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/detached-house")
    public ResponseEntity<Void> saveDetachedHouse(@Valid @RequestBody DetachedHouseRequest request){
        realEstateListingService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateStatus(@RequestParam ListingStatus status, @RequestParam Long id){
        realEstateListingService.updateStatus(status,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<RealEstateListingResponse>> searchAll(@RequestBody RealEstateListingSearchRequest request){

        return new ResponseEntity<>(realEstateListingService.searchAll(request),HttpStatus.OK);
    }
}
