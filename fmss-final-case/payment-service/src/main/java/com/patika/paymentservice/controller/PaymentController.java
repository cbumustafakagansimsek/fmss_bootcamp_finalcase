package com.patika.paymentservice.controller;

import com.patika.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/validate/{userId}")
    public ResponseEntity<Boolean> paymentValidate(@PathVariable Long userId){
        return ResponseEntity.ok(paymentService.paymentValidation(userId));
    }
}
