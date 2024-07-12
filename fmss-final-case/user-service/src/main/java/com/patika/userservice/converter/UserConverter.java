package com.patika.userservice.converter;

import com.patika.userservice.dto.request.UserRequest;
import com.patika.userservice.dto.response.UserResponse;
import com.patika.userservice.model.Role;
import com.patika.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toUser(UserRequest request){
        return User.builder()
                .mail(request.getMail())
                .name(request.getName())
                .surname(request.getSurname())
                .role(Role.INITIAL)
                .password(request.getPassword())
                .build();
    }

    public UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .mail(user.getMail())
                .role(user.getRole())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }
}
