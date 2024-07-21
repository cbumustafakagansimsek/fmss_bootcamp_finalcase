package com.patika.userservice.producer.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;

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
