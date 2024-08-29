package com.payments.vipay.services;

import com.payments.vipay.data.PaymentRequest;

public interface PaymentServiceInt {

    void processPayment(PaymentRequest paymentRequest);
}
