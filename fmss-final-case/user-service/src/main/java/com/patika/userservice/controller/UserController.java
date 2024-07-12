package com.patika.userservice.controller;

import com.patika.userservice.dto.request.LoginRequest;
import com.patika.userservice.dto.request.UserRequest;
import com.patika.userservice.dto.response.UserResponse;
import com.patika.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;




    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        log.info("Rest request to find user by id:{}",id);
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PutMapping("role/subscribed/{id}")
    public ResponseEntity<UserResponse> updateRoleAsSubscribed(@PathVariable Long id){
        log.info("Rest request to change user role as Subscribed by Id: {}",id);
        return new ResponseEntity<>(userService.updateRoleAsSubscribed(id), HttpStatus.OK);
    }

    @PutMapping("role/initial/{id}")
    public ResponseEntity<UserResponse> updateRoleAsInitial(@PathVariable Long id){
        log.info("Rest request to change user role as Initial by Id: {}",id);
        return new ResponseEntity<>(userService.updateRoleAsInitial(id), HttpStatus.OK);
    }
}
