package com.homecommerce.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.models.Payment;
import com.homecommerce.repos.PaymentRepository;

class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSavePayment() {
        Payment payment = mock(Payment.class);
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentService.savePayment(payment);

        assertEquals(payment, savedPayment);
    }

    @Test
    void testFindPaymentById() {
        Payment payment = mock(Payment.class);
        when(paymentRepository.findById(1)).thenReturn(java.util.Optional.of(payment));

        Payment foundPayment = paymentService.findPaymentById(1);

        assertEquals(payment, foundPayment);
    }
}

