package com.payments.vipay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PaymentContoller {

    @Autowired
    private final KafkaTemplate<String,PaymentRequest> kafkaTemplate;


    @PostMapping
    public ResponseEntity sendMoney() throws InterruptedException{

        return new ResponseEntity<>(Map.of("message","Payment sent"), HttpStatus.OK);
    }
}
