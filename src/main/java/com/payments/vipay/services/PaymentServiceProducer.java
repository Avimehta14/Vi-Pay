package com.payments.vipay.services;

import com.payments.vipay.data.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceProducer implements PaymentServiceInt {

    @Autowired
    private KafkaTemplate<String, PaymentRequest> kafkaTemplate;

    @Override
    public void processPayment(PaymentRequest paymentRequest) {
        kafkaTemplate.send("payment_initiated",paymentRequest);
    }
}
