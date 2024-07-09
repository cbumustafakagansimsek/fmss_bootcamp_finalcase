package com.patika.subscriptionservice.client.user.service;


import com.patika.subscriptionservice.client.user.UserClient;
import com.patika.subscriptionservice.client.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserClient userClient;

    public UserResponse findById(Long id){
        ResponseEntity<UserResponse> response = userClient.findById(id);
//        if (response == null || !HttpStatus.OK.equals(response.getStatusCode())) {
//
//            log.error("Error Message: {}", response.getBody());
//            throw new KitapYurdumException(response.getMessage());
//        }

        return response.getBody();
    }

    public UserResponse updateRoleAsSubscribed(@PathVariable Long id){
        ResponseEntity<UserResponse> response = userClient.updateRoleAsSubscribed(id);
        return response.getBody();
    }
    public UserResponse updateRoleAsInitial(@PathVariable Long id){
        ResponseEntity<UserResponse> response = userClient.updateRoleAsInitial(id);
        return response.getBody();
    }
}
