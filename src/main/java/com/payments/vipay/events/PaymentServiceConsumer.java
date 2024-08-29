package com.payments.vipay.events;

import com.payments.vipay.data.PaymentRequest;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;

public class PaymentServiceConsumer
{

    @KafkaListener(topics= "payment_initiated", groupId = "payment-group", containerFactory = "kafkaListenerContainerFactory")
    public void topicCosnumer(PaymentRequest paymentRequest) throws Exception {
        try {
            processPayments(paymentRequest);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    private void processPayments(PaymentRequest paymentRequest) throws Exception {

        if (paymentRequest.getAmount() < 0)
        {
            throw new Exception("Inavlid Payment Amount");
        }
        System.out.println("payment Processed Successfuly");
    }

    @DltHandler
    public void handleDlt(PaymentRequest paymentRequest)
    {
        System.err.println("handling error in message" + paymentRequest);
    }
}
