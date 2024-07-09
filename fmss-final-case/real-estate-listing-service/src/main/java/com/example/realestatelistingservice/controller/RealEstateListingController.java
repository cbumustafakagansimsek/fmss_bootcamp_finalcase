package com.example.realestatelistingservice.controller;


import com.example.realestatelistingservice.dto.request.FlatRequest;
import com.example.realestatelistingservice.dto.request.VillaRequest;
import com.example.realestatelistingservice.model.ListingStatus;
import com.example.realestatelistingservice.model.RealEstateListing;
import com.example.realestatelistingservice.service.RealEstateListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/listing")
@RequiredArgsConstructor
public class RealEstateListingController {

    private final RealEstateListingService realEstateListingService;

    @PostMapping("/flat")
    public ResponseEntity<Void> saveFlat(@RequestBody FlatRequest request){
        realEstateListingService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/villa")
    public ResponseEntity<Void> saveVilla(@RequestBody VillaRequest request){
        realEstateListingService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateStatus(@RequestParam ListingStatus status, @RequestParam Long id){
        realEstateListingService.updateStatus(status,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
