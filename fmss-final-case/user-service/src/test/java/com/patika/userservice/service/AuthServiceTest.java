package com.patika.userservice.service;




import com.patika.userservice.auth.JwtProvider;
import com.patika.userservice.auth.UserSecurityInfo;
import com.patika.userservice.dto.request.LoginRequest;
import com.patika.userservice.dto.request.UserRequest;
import com.patika.userservice.dto.response.UserResponse;
import com.patika.userservice.exception.TokenIsNotValidException;
import com.patika.userservice.model.Role;
import com.patika.userservice.producer.log.LogProducer;
import com.patika.userservice.producer.log.LogRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private LogProducer logProducer;

    @Mock
    private JwtProvider jwtTokenProvider;

    @InjectMocks
    private AuthService authService;

    private LoginRequest loginRequest;
    private UserRequest userRequest;
    private Authentication auth;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest("test@mail.com", "password");
        userRequest = UserRequest.builder()
                .mail("test@mail.com")
                .name("Test")
                .surname("User")
                .password("password")
                .build();

        auth = mock(Authentication.class);
        jwtToken = "jwtToken";
    }

    @Test
    void testLogin_returnUserSecurityInfo() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(jwtTokenProvider.generateJwtToken(auth)).thenReturn(jwtToken);
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        UserSecurityInfo securityInfo = authService.login(loginRequest);

        assertEquals(jwtToken, securityInfo.getToken());

        verify(logProducer).sendLog(any(LogRequest.class));
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider).generateJwtToken(auth);
    }

    @Test
    void testRegister_succesfully() {
        doNothing().when(logProducer).sendLog(any(LogRequest.class));

        boolean result = authService.register(userRequest);

        assertTrue(result);

        verify(logProducer).sendLog(any(LogRequest.class));
        verify(userService).save(userRequest);
    }

    @Test
    void testValidateToken_returnUserResponse() {
        when(jwtTokenProvider.validateToken(jwtToken)).thenReturn(true);
        when(jwtTokenProvider.getUserIdFromJwt(jwtToken)).thenReturn(1L);
        UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .mail("test@mail.com")
                .name("Test")
                .surname("User")
                .role(Role.SUBSCRIPTED)
                .build();
        when(userService.findById(1L)).thenReturn(userResponse);

        UserResponse result = authService.validateToken(jwtToken);

        assertEquals(userResponse, result);

        verify(jwtTokenProvider).validateToken(jwtToken);
        verify(jwtTokenProvider).getUserIdFromJwt(jwtToken);
        verify(userService).findById(1L);
    }

    @Test
    void testValidateTokenInvalid_returnThrowTokenIsNotValidException() {
        when(jwtTokenProvider.validateToken(jwtToken)).thenReturn(false);

        TokenIsNotValidException exception = assertThrows(TokenIsNotValidException.class, () -> {
            authService.validateToken(jwtToken);
        });

        assertEquals("Token is not valid", exception.getMessage());

        verify(jwtTokenProvider).validateToken(jwtToken);
        verify(jwtTokenProvider, never()).getUserIdFromJwt(anyString());
        verify(userService, never()).findById(anyLong());
    }
}
