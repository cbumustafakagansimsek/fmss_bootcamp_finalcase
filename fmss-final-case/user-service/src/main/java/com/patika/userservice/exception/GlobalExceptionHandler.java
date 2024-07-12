package com.patika.userservice.exception;

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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionMessage> userNotFoundException(UserNotFoundException exception) throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ExceptionMessage.builder()
                .error(HttpStatus.BAD_REQUEST.name())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistUserException.class)
    public ResponseEntity<ExceptionMessage> ExistUserException(ExistUserException exception) throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ExceptionMessage.builder()
                .error(HttpStatus.BAD_REQUEST.name())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenIsNotValidException.class)
    public ResponseEntity<ExceptionMessage> tokenIsNotValidException(TokenIsNotValidException exception) throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ExceptionMessage.builder()
                .error(HttpStatus.UNAUTHORIZED.name())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),HttpStatus.UNAUTHORIZED);
    }

}
