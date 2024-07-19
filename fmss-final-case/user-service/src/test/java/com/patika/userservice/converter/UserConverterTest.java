package com.patika.userservice.converter;

import com.patika.userservice.dto.request.UserRequest;
import com.patika.userservice.dto.response.UserResponse;
import com.patika.userservice.model.Role;
import com.patika.userservice.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {
    @InjectMocks
    private UserConverter userConverter;

    @Test
    void testToResponse_shouldReturnUserResponse() {
        User user = User.builder()
                .id(1L)
                .name("Test")
                .role(Role.INITIAL)
                .build();

        UserResponse response = userConverter.toResponse(user);

        assertEquals(user.getId(), response.getId());
        assertEquals(user.getName(), response.getName());
        assertEquals(user.getRole(), response.getRole());
    }

    @Test
    void testToUser_shouldReturnUser() {
        UserRequest userRequest = UserRequest.builder()
                .mail("test@mail.com")
                .name("Test")
                .surname("Surname")
                .password("Password")
                .build();

        User response = userConverter.toUser(userRequest);

        assertEquals("test@mail.com", response.getMail());
        assertEquals("Test", response.getName());
        assertEquals("Surname", response.getSurname());
        assertEquals("Password", response.getPassword());

    }


}
