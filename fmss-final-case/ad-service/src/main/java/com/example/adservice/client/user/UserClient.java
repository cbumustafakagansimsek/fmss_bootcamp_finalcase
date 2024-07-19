package com.example.adservice.client.user;

import com.example.adservice.client.user.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "user-service",path = "/api/v1/user")
public interface UserClient {
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable Long id);

//    @PutMapping("role/subscribed/{id}")
//    ResponseEntity<UserResponse> updateRoleAsSubscribed(@PathVariable Long id);
//    @PutMapping("role/initial/{id}")
//    ResponseEntity<UserResponse> updateRoleAsInitial(@PathVariable Long id);
}
