package com.patika.userservice.service;


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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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

    private UserResponse userResponse;

    private User user;


    @BeforeEach
    void setUp() {
        userRequest = UserRequest.builder()
                .mail("test@mail.com")
                .name("Name")
                .surname("Surname")
                .password("password")
                .build();

        user = User.builder()
                .id(USER_ID)
                .mail("test@mail.com")
                .name("Name")
                .surname("Surname")
                .role(Role.INITIAL)
                .password("encodedPassword")
                .build();


        userResponse = UserResponse.builder()
                .id(USER_ID)
                .mail("test@mail.com")
                .name("Name")
                .surname("Surname")
                .role(Role.INITIAL)
                .build();


    }

    @Test
    void testSaveUser_successfully() {
        when(userRepository.existsUserByMail(userRequest.getMail())).thenReturn(false);
        when(passwordEncoder.encode(userRequest.getPassword())).thenReturn("encodedPassword");

        userService.save(userRequest);

        verify(userRepository, times(1)).save(any(User.class));
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

    @Test
    void testUpdateRoleAsSubscribed_shouldReturnUserResponse() {

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(converter.toResponse(any(User.class))).thenReturn(userResponse);

        UserResponse result = userService.updateRoleAsSubscribed(USER_ID);

        assertEquals(Role.SUBSCRIPTED, user.getRole());
        assertEquals(userResponse, result);

        verify(userRepository, times(1)).findById(USER_ID);
        verify(userRepository, times(1)).save(user);
        verify(converter, times(1)).toResponse(user);
    }

    @Test
    void testUpdateRoleAsInitial_shouldReturnUserResponse() {
        user.setRole(Role.SUBSCRIPTED);
        userResponse.setRole(Role.INITIAL);

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(converter.toResponse(any(User.class))).thenReturn(userResponse);

        UserResponse response = userService.updateRoleAsInitial(USER_ID);

        assertEquals(Role.INITIAL, user.getRole());
        assertEquals(userResponse, response);

        verify(userRepository, times(1)).findById(USER_ID);
        verify(userRepository, times(1)).save(user);
        verify(converter, times(1)).toResponse(user);
    }

    @Test
    void testUpdateRoleAsSubscribed_shouldReturnUserNotFound() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.updateRoleAsSubscribed(USER_ID);
        });

        assertEquals("User not found by id:" + USER_ID, exception.getMessage());

        verify(userRepository, times(1)).findById(USER_ID);
        verify(userRepository, times(0)).save(any(User.class));
        verifyNoInteractions(converter);
    }

    @Test
    void testUpdateRoleAsInitial_shouldReturnUserNotFound() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.updateRoleAsInitial(USER_ID);
        });

        assertEquals("User not found by id:" + USER_ID, exception.getMessage());

        verify(userRepository, times(1)).findById(USER_ID);
        verify(userRepository, times(0)).save(any(User.class));
        verifyNoInteractions(converter);
    }
}
