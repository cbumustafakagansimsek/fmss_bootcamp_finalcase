package com.patika.userservice.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserSecurityInfo {
    private String email;
    private String name;
    private Long id;
    @Value("${finalcase.expires.in}")
    private Long expiresIn ;
    private String token;
}
