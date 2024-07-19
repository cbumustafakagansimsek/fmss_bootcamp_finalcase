package com.example.adactivationservice.client.ad;

import com.example.adactivationservice.client.ad.dto.AdStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ad-service",path = "/api/v1/ads")
public interface AdClient {

    @PutMapping("/secure/status")
     ResponseEntity<Void> updateStatus(@RequestParam AdStatus status,@RequestParam Long id,@RequestHeader("x-auth-user-id") Long userId);

}
