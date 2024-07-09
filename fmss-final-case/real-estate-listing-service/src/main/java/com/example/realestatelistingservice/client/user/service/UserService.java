package com.example.realestatelistingservice.client.user.service;



import com.example.realestatelistingservice.client.user.UserClient;
import com.example.realestatelistingservice.client.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

}
