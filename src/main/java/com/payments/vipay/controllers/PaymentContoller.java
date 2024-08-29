package com.payments.vipay.controllers;

import com.payments.vipay.data.PaymentRequest;
import com.payments.vipay.services.PaymentServiceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PaymentContoller {

    @Autowired
    private PaymentServiceProducer paymentServiceProducer;

    @PostMapping
    public ResponseEntity sendMoney(@RequestBody PaymentRequest paymentRequest) throws InterruptedException{
        try
        {
            paymentServiceProducer.processPayment(paymentRequest);
            return new ResponseEntity<>(Map.of("message","Payment sent"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(Map.of("message","Payment Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
