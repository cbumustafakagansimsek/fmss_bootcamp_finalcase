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
    private String token;
}
