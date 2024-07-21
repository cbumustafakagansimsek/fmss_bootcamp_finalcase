package com.example.adservice.producer.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class LogDto {
    private String service;
    private String level;
    private String classOfService;
    private String message;
    private Date time;
}
