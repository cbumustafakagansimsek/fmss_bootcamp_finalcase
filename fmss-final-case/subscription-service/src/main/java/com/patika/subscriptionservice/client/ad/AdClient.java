package com.patika.subscriptionservice.client.ad;

import com.patika.subscriptionservice.client.ad.service.AdStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ad-service",path = "/api/v1/ads")
public interface AdClient {
    @PutMapping("secure/user/status")
    ResponseEntity<Void> updateAllStatusByUser(@RequestParam AdStatus status,
                                                      @RequestHeader("x-auth-user-id") Long id);
}
