package com.patika.subscriptionservice.client.user.service;


import com.patika.subscriptionservice.client.user.UserClient;
import com.patika.subscriptionservice.client.user.response.UserResponse;
import com.patika.subscriptionservice.exception.ClientException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import feign.FeignException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserClient userClient;

    @InjectMocks
    private UserService userService;


    @Test
    void testUpdateRoleAsSubscribed_succesfully() {
        Long userId = 1L;

        UserResponse userResponse = new UserResponse();
        ResponseEntity<UserResponse> responseEntity = ResponseEntity.ok(userResponse);

        when(userClient.updateRoleAsSubscribed(userId)).thenReturn(responseEntity);

        UserResponse response = userService.updateRoleAsSubscribed(userId);

        assertNotNull(response);
        assertEquals(userResponse, response);
        verify(userClient, times(1)).updateRoleAsSubscribed(userId);
    }

    @Test
    void testUpdateRoleAsSubscribed_failure() {
        Long userId = 1L;

        FeignException feignException = mock(FeignException.class);
        when(feignException.contentUTF8()).thenReturn("Error message");
        when(userClient.updateRoleAsSubscribed(userId)).thenThrow(feignException);

        ClientException thrown = assertThrows(ClientException.class, () -> {
            userService.updateRoleAsSubscribed(userId);
        });

        assertEquals("Error message", thrown.getMessage());
        verify(userClient, times(1)).updateRoleAsSubscribed(userId);
    }

    @Test
    void testUpdateRoleAsInitial_Success() {
        Long userId = 1L;

        UserResponse userResponse = new UserResponse();
        ResponseEntity<UserResponse> responseEntity = ResponseEntity.ok(userResponse);

        when(userClient.updateRoleAsInitial(userId)).thenReturn(responseEntity);

        UserResponse response = userService.updateRoleAsInitial(userId);

        assertNotNull(response);
        assertEquals(userResponse, response);
        verify(userClient, times(1)).updateRoleAsInitial(userId);
    }

    @Test
    void testUpdateRoleAsInitial_Failure() {
        Long userId = 1L;

        FeignException feignException = mock(FeignException.class);
        when(feignException.contentUTF8()).thenReturn("Error message");
        when(userClient.updateRoleAsInitial(userId)).thenThrow(feignException);

        ClientException thrown = assertThrows(ClientException.class, () -> {
            userService.updateRoleAsInitial(userId);
        });

        assertEquals("Error message", thrown.getMessage());
        verify(userClient, times(1)).updateRoleAsInitial(userId);
    }
}