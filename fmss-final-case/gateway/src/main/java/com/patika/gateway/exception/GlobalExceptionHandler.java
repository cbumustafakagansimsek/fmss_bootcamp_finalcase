package com.patika.gateway.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.io.IOException;
import java.time.LocalDateTime;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionMessage> subscriptionNotFoundException(AuthException exception) throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ExceptionMessage.builder()
                .error(HttpStatus.UNAUTHORIZED.name())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),HttpStatus.UNAUTHORIZED);
    }

}
