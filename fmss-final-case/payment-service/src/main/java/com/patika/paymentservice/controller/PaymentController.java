package com.patika.paymentservice.controller;

import com.patika.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/validate")
    public ResponseEntity<Boolean> paymentValidate(@RequestHeader("x-auth-user-id") Long userId){
        log.info("Rest request for payment validate by userId:{}",userId);
        return ResponseEntity.ok(paymentService.paymentValidation(userId));
    }
}
