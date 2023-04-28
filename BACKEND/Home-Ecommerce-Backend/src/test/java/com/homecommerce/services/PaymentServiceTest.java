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
    /**
     This is a test class for the PaymentService class which handles operations related to Payment model.
     The tests in this class ensure that the PaymentService class is working as intended.
     It uses the Mockito framework to mock the PaymentRepository dependency, and test the behavior of the PaymentService methods.
     The first test method, testSavePayment(), verifies that the savePayment() method in the PaymentService class saves a payment
     using the PaymentRepository, and returns the saved payment.
     The second test method, testFindPaymentById(), verifies that the findPaymentById() method in the PaymentService class retrieves
     a payment using the PaymentRepository, and returns the payment.
     The class also achieves 100% line coverage for the methods savePayment() and findPaymentById().
     */

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

