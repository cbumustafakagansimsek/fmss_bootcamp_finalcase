package com.patika.userservice.controller;

import com.patika.userservice.auth.UserSecurityInfo;
import com.patika.userservice.dto.request.LoginRequest;
import com.patika.userservice.dto.request.UserRequest;
import com.patika.userservice.dto.response.UserResponse;
import com.patika.userservice.service.AuthService;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<UserSecurityInfo> login(@Valid @RequestBody LoginRequest request){

        log.info("REST Request to login : {}",request.getMail());

        return new ResponseEntity<>(authService.login(request), HttpStatus.ACCEPTED);
    }
    @PostMapping("/register")
    public ResponseEntity<Boolean> register (@Valid @RequestBody UserRequest request){
        log.info("Rest request to save user {}",request);
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<UserResponse> validateToken (@RequestParam String token){
        log.info("REST Request to validate token");

        return new ResponseEntity<>(authService.validateToken(token), HttpStatus.OK);
    }

}
