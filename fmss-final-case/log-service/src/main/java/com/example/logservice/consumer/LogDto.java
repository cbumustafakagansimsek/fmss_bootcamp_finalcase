package com.example.logservice.consumer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.Level;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class LogDto {
    private String service;

    private String level;
    private String classOfService;
    private String message;

    @JsonProperty("time")
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date time;
}
