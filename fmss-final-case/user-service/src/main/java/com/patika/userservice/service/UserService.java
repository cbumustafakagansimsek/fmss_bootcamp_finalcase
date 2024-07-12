package com.patika.userservice.service;

import com.patika.userservice.converter.UserConverter;
import com.patika.userservice.dto.request.UserRequest;
import com.patika.userservice.dto.response.UserResponse;
import com.patika.userservice.exception.ExistUserException;
import com.patika.userservice.exception.UserNotFoundException;
import com.patika.userservice.model.Role;
import com.patika.userservice.model.User;
import com.patika.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final UserConverter converter;

    public void save(UserRequest request){
        log.info("Request to register for User: {}",request);

        if(userRepository.existsUserByMail(request.getMail())){
            throw new ExistUserException("User is exist "+ request.getMail());
        }

        User user = User.builder()
                .mail(request.getMail())
                .name(request.getName())
                .surname(request.getSurname())
                .role(Role.INITIAL)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }

    public UserResponse findById(Long id){
        log.info("Request to find User by Id: {}",id);

        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found by id:"+id));
        return converter.toResponse(user);
    }

    public UserResponse updateRoleAsSubscribed(Long id){
        log.info("Request to change user role as Subscribed by Id: {}",id);

        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found by id:"+id));
        user.setRole(Role.SUBSCRIPTED);

       return converter.toResponse(userRepository.save(user)) ;
    }

    public UserResponse updateRoleAsInitial(Long id){
        log.info("Request to change user role as Initial by Id: {}",id);

        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found by id:"+id));
        user.setRole(Role.INITIAL);

        return converter.toResponse(userRepository.save(user));
    }

    protected boolean isExistUser(String mail){
        log.info("Request to change user role as Initial by Id: {}",mail);

        return userRepository.existsUserByMail(mail);
    }
}
