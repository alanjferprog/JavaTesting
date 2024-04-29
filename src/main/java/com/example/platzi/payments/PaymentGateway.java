package com.example.platzi.payments;

public interface PaymentGateway
{
    PaymentResponse requestPayment (PaymentRequest request);
}
