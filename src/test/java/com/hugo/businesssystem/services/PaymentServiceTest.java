package com.hugo.businesssystem.services;

import com.hugo.businesssystem.entities.Payment;
import com.hugo.businesssystem.repositories.PaymentRepository;
import com.hugo.businesssystem.util.payment.PaymentCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;
    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp(){

        List<Payment> payments = List.of(PaymentCreator.createValidPayment());
        BDDMockito.when(paymentRepository.findAll())
                .thenReturn(payments);

        BDDMockito.when(paymentRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(PaymentCreator.createValidPayment()));
    }

    @Test
    void findAll_ReturnsListOfPayments_WhenSuccessful(){

        LocalDateTime expectedInstant = PaymentCreator.createValidPayment().getInstantPay();
        List<Payment> payments = paymentService.findAll();

        Assertions.assertThat(payments)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(payments.get(0).getInstantPay()).isEqualTo(expectedInstant);
    }
    @Test
    void findById_ReturnsPayment_WhenSuccessful(){

        Long expectedId = PaymentCreator.createValidPayment().getId();
        Payment payment = paymentService.findById(1L);

        Assertions.assertThat(payment).isNotNull();
        Assertions.assertThat(payment.getId()).isNotNull().isEqualTo(expectedId);
    }
}