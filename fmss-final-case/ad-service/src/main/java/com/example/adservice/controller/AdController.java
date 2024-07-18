package com.example.adservice.controller;



import com.example.adservice.dto.request.DetachedHouseRequest;
import com.example.adservice.dto.request.FlatRequest;
import com.example.adservice.dto.request.AdSearchRequest;
import com.example.adservice.dto.request.VillaRequest;
import com.example.adservice.dto.response.AdResponse;
import com.example.adservice.dto.response.SearchResponse;
import com.example.adservice.model.AdStatus;
import com.example.adservice.service.AdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/ads")
@RequiredArgsConstructor
@Slf4j
public class AdController {

    private final AdService adService;

    @PostMapping("/secure/flat")
    public ResponseEntity<Void> saveFlat(@RequestHeader("x-auth-user-id") Long userId,
                                         @Valid @RequestBody FlatRequest request){
        log.info("REST Request to save real estate ad as flat: {}", request);
        adService.save(request,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/secure/villa")
    public ResponseEntity<Void> saveVilla(@RequestHeader("x-auth-user-id") Long userId,
                                          @Valid @RequestBody VillaRequest request){
        log.info("REST Request to save real estate ad as villa: {}", request);

        adService.save(request,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/secure/detached-house")
    public ResponseEntity<Void> saveDetachedHouse(@RequestHeader("x-auth-user-id") Long userId,
                                                  @Valid @RequestBody DetachedHouseRequest request){

        log.info("REST Request to save real estate ad as detachen house: {}", request);

        adService.save(request,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateStatus(@RequestParam AdStatus status,
                                             @RequestParam Long id){
        log.info("REST Request to update status as {} by id:{}",status,id);

        adService.updateStatus(status,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<SearchResponse> searchAll(AdSearchRequest request){
        log.info("REST Request to search all real estate ad by:{}",request);

        return new ResponseEntity<>(adService.searchAll(request),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdResponse> findById(@PathVariable Long id){
        log.info("REST Request to find by id {}",id);

        return new ResponseEntity<>(adService.findById(id),HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<AdResponse>> findAllByUserId(@PathVariable Long userId,
                                                            @RequestParam(value = "status",required = false) Optional<AdStatus> status){
        log.info("REST Request to find by id {}",userId);

        return new ResponseEntity<>(adService.findAllByUserId(userId,status),HttpStatus.OK);
    }
}
