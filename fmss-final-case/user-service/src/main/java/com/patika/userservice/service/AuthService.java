package com.patika.userservice.service;

import com.patika.userservice.auth.JwtProvider;
import com.patika.userservice.auth.UserSecurityInfo;
import com.patika.userservice.dto.request.LoginRequest;
import com.patika.userservice.dto.request.UserRequest;
import com.patika.userservice.dto.response.UserResponse;
import com.patika.userservice.exception.TokenIsNotValidException;
import com.patika.userservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    @Value("${finalcase.app.secret}")
    private String APP_SECRET ;
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtTokenProvider;


    public UserSecurityInfo login(LoginRequest request){
        log.debug("Request to login : {}",request);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return UserSecurityInfo.builder().token(jwtToken).build();
    }

    public boolean register(UserRequest request){
        log.debug("Request to register for User: {}",request);

        userService.save(request);
        return true;
    }

    public UserResponse validateToken(String token) {
        if(!jwtTokenProvider.validateToken(token)){
            throw new TokenIsNotValidException("Token is not valid");
        }

        return userService.findById(jwtTokenProvider.getUserIdFromJwt(token));
    }
}
