package com.patika.userservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginRequest {

    @NotBlank(message = "Mail is mandatory")
    private String mail;

    @NotBlank(message = "Password is mandatory")
    private String password;

}
