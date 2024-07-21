package com.example.logservice.model;

import lombok.Builder;
import org.apache.logging.log4j.Level;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "logs")
@Builder
public class Log {

    @Id
    private String id;
    private String service;
    private String level;
    private String classOfService;
    private String message;
    private Date time;



}
