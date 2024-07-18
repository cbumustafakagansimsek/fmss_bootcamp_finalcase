package com.example.adservice.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors =new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ExceptionMessage> clientException(ClientException exception) throws IOException {
        log.error(exception.getMessage());
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        ExceptionMessage message = mapper.readValue(exception.getMessage(), ExceptionMessage.class);
        return new ResponseEntity<>(message,HttpStatusCode.valueOf(message.getStatus()));
    }

    @ExceptionHandler(ExhaustedAdLimitException.class)
    public ResponseEntity<ExceptionMessage> exhaustedAdLimitException(ExhaustedAdLimitException exception) throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ExceptionMessage.builder()
                .error(HttpStatus.BAD_REQUEST.name())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HouseTypeIsInvalidException.class)
    public ResponseEntity<ExceptionMessage> houseTypeIsInvalidException(HouseTypeIsInvalidException exception) throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ExceptionMessage.builder()
                .error(HttpStatus.BAD_REQUEST.name())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdNotFoundException.class)
    public ResponseEntity<ExceptionMessage> realEstateAdNotFoundException(AdNotFoundException exception) throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ExceptionMessage.builder()
                .error(HttpStatus.BAD_REQUEST.name())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),HttpStatus.BAD_REQUEST);
    }

}
