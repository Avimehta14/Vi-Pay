package com.payments.vipay.events;

import com.payments.vipay.data.PaymentRequest;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentServiceConsumer
{
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceConsumer.class);

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
            logger.error("Payament is invalid");
            throw new Exception("Inavlid Payment Amount");
        }
        System.out.println("payment Processed Successfuly");
        logger.error("payment not processed !!!!!");
    }

    @DltHandler
    public void handleDlt(PaymentRequest paymentRequest)
    {
        logger.error("DLT handling the error");
        System.err.println("handling error in message" + paymentRequest);
    }
}
