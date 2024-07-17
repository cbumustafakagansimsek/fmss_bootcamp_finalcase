package com.patika.userservice.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.patika.userservice.converter.UserConverter;
import com.patika.userservice.dto.request.UserRequest;
import com.patika.userservice.dto.response.UserResponse;
import com.patika.userservice.exception.ExistUserException;
import com.patika.userservice.exception.UserNotFoundException;
import com.patika.userservice.model.Role;
import com.patika.userservice.model.User;
import com.patika.userservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter converter;

    @InjectMocks
    private UserService userService;

    private static final Long USER_ID = 1L;

    private UserRequest userRequest;
    private User user;

    @BeforeEach
    void setUp() {
        userRequest = UserRequest.builder()
                .mail("test@mail.com")
                .name("Test")
                .surname("User")
                .password("password")
                .build();

        user = User.builder()
                .id(USER_ID)
                .mail("test@mail.com")
                .name("Test")
                .surname("User")
                .role(Role.INITIAL)
                .password("encodedPassword")
                .build();
    }

    @Test
    void testSaveUser_Success() {
        when(userRepository.existsUserByMail(userRequest.getMail())).thenReturn(false);
        when(passwordEncoder.encode(userRequest.getPassword())).thenReturn("encodedPassword");

        userService.save(userRequest);

        verify(userRepository, times(1)).save(Mockito.any(User.class));
    }

    @Test
    void testSaveUser_withExistUser() {
        when(userRepository.existsUserByMail(userRequest.getMail())).thenReturn(true);

        ExistUserException thrown = assertThrows(ExistUserException.class, () -> {
            userService.save(userRequest);
        });

        assertEquals("User is exist test@mail.com", thrown.getMessage());
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void testFindById_shouldReturnUserResponse() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(converter.toResponse(user)).thenReturn(new UserResponse());

        UserResponse result = userService.findById(USER_ID);

        assertNotNull(result);
        verify(userRepository, times(1)).findById(USER_ID);
    }

    @Test
    void testFindById_shouldThrowUserNotFoundException() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());
        UserNotFoundException userNotFoundException = Assertions.assertThrows(UserNotFoundException.class, () -> userService.findById(USER_ID));

        assertThat(userNotFoundException.getMessage()).isEqualTo("User not found by id:1");

        verify(userRepository, times(1)).findById(USER_ID);
        verifyNoInteractions(converter);
    }
}
