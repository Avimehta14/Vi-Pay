package com.payments.vipay.controllers;

import com.payments.vipay.data.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PaymentContoller {

    @Autowired
    private KafkaTemplate<String, PaymentRequest> kafkaTemplate;

    @PostMapping
    public ResponseEntity sendMoney(@RequestBody PaymentRequest paymentRequest) throws InterruptedException{

        return new ResponseEntity<>(Map.of("message","Payment sent"), HttpStatus.OK);
    }
}
