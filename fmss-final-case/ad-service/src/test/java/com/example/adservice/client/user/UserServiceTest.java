package com.example.adservice.client.user;

import com.example.adservice.client.user.response.UserResponse;
import com.example.adservice.client.user.service.UserService;
import com.example.adservice.exception.ClientException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserClient userClient;

    private static final Long USER_ID=1L;

    @Test
    void findCurrentSubscription_successfully(){
        ResponseEntity<UserResponse> responseEntity = ResponseEntity.ok(new UserResponse());

        when(userClient.findById(USER_ID)).thenReturn(responseEntity);

        userService.findById(USER_ID);


        verify(userClient,times(1)).findById(USER_ID);
    }

    @Test
    void findCurrentSubscription_failure(){

        when(userClient.findById(USER_ID)).thenThrow(new ClientException("Error message"));
        ClientException exception = assertThrows(ClientException.class,()->userClient.findById(USER_ID));

        assertEquals("Error message", exception.getMessage());

        verify(userClient,times(1)).findById(USER_ID);
    }
}
