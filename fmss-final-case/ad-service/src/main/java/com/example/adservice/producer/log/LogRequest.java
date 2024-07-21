package com.example.adservice.producer.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.Level;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class LogRequest {
    private String service;
    private Level level;
    private String classOfService;
    private String message;
    private Date time;
}
