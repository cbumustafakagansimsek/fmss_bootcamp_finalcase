package com.patika.gateway.filter;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payload {
    private Long id;

    private String name;

    private AuthRole role;
    private String surname;

    private String mail;


}
