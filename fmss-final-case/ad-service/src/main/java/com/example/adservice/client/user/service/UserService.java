package com.example.adservice.client.user.service;




import com.example.adservice.client.user.UserClient;
import com.example.adservice.client.user.response.UserResponse;
import com.example.adservice.exception.ClientException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserClient userClient;

    public UserResponse findById(Long id){
        try {
            ResponseEntity<UserResponse> response = userClient.findById(id);
            return response.getBody();
        }catch (FeignException e){
            throw new ClientException(e.contentUTF8());
        }
    }

}
