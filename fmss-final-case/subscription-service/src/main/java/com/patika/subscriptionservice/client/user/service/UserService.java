package com.patika.subscriptionservice.client.user.service;


import com.patika.subscriptionservice.client.user.UserClient;
import com.patika.subscriptionservice.client.user.response.UserResponse;
import com.patika.subscriptionservice.exception.ClientException;
import feign.FeignException;
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

    public UserResponse updateRoleAsSubscribed(@PathVariable Long id){
        try{
            ResponseEntity<UserResponse> response = userClient.updateRoleAsSubscribed(id);
            return response.getBody();
        }catch (FeignException e){
            throw new ClientException(e.contentUTF8());
        }
    }
    public UserResponse updateRoleAsInitial(@PathVariable Long id){
        try {
            ResponseEntity<UserResponse> response = userClient.updateRoleAsInitial(id);
            return response.getBody();
        }
        catch (FeignException e){
            throw new ClientException(e.contentUTF8());
        }
    }
}
