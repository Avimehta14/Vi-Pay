package com.payments.vipay.events;

import com.payments.vipay.data.PaymentRequest;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceConsumer.class);

    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 1000)
    )
    @KafkaListener(topics = "payment_initiated", groupId = "payment-group", containerFactory = "kafkaListenerContainerFactory")
    public void topicConsumer(PaymentRequest paymentRequest) throws Exception {
        logger.info("Received payment request: {}", paymentRequest);
        try {
            validateRequest(paymentRequest);
        } catch (Exception e) {
            logger.error("Error processing payment: {}", paymentRequest, e);
            throw new RuntimeException(e);
        }

    }

    private void validateRequest(PaymentRequest paymentRequest) throws Exception {
        if (paymentRequest == null || paymentRequest.getUserId() == null || paymentRequest.getAmount() < 0) {
            logger.error("Invalid payment request: {}", paymentRequest);
            throw new RuntimeException("Invalid Payment Request");
        }
        else {
            logger.info("Successful Payment");
            System.out.println("Payment sent ...Lesgooo");
        }
    }

    @DltHandler
    public void handleDlt(PaymentRequest paymentRequest) {
        logger.error("Handling message in DLT: {}", paymentRequest);
        System.err.println("Handling error in message: " + paymentRequest);
    }
}
