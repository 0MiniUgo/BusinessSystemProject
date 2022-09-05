package com.hugo.businesssystem.util.payment;

import com.hugo.businesssystem.entities.Payment;

import java.time.LocalDateTime;

public class PaymentCreator {
    public static Payment createValidPayment(){
        return Payment.builder()
                .id(1L)
                .instantPay(LocalDateTime.of(2022, 9, 5, 15, 23, 30))
                .build();
    }
}
