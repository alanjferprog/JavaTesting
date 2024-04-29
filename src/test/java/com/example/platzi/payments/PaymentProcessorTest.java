package com.example.platzi.payments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PaymentProcessorTest
{
    private PaymentGateway paymentGateway;
    private PaymentProcessor paymentProcessor;

    @BeforeEach
    public void setup() {
         paymentGateway = Mockito.mock(PaymentGateway.class);
         paymentProcessor= new PaymentProcessor(paymentGateway);
    }

    @Test
    void payment_is_correct() {
         //1. Preparacion del escenario

        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.OK));


         //2. Llamada al metodo que queremos probar
        Boolean result =paymentProcessor.makePayment(1000);

         //3. Comprobacion del resultado
        assertTrue(result);
    }

    @Test
    void payment_is_wrong() {

        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.ERROR));

        assertFalse(paymentProcessor.makePayment(1000));
    }
}