package com.homecommerce.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void testGetSetId() {
        Payment payment = new Payment();
        payment.setId(1);
        assertEquals(1, payment.getId());
    }

    @Test
    void testGetSetCardno() {
        Payment payment = new Payment();
        payment.setCardno("1234567890123456");
        assertEquals("1234567890123456", payment.getCardno());
    }

    @Test
    void testGetSetNameOnCard() {
        Payment payment = new Payment();
        payment.setNameoncard("John Doe");
        assertEquals("John Doe", payment.getNameoncard());
    }

    @Test
    void testGetSetAmount() {
        Payment payment = new Payment();
        payment.setAmount(100);
        assertEquals(100, payment.getAmount());
    }

    @Test
    void testGetSetPaymentDate() {
        Payment payment = new Payment();
        LocalDate now = LocalDate.now();
        payment.setPaymentdate(now);
        assertEquals(now, payment.getPaymentdate());
    }

    @Test
    void testConstructor() {
        Payment payment = new Payment();
        assertNotNull(payment.getPaymentdate());
    }

    @Test
    void testToString() {
        Payment payment = new Payment();
        payment.setId(1);
        payment.setCardno("1234567890123456");
        payment.setNameoncard("John Doe");
        payment.setAmount(100);
        payment.setPaymentdate(LocalDate.of(2023, 4, 18));

        assertEquals("Payment [id=1, cardno=1234567890123456, nameoncard=John Doe, amount=100, paymentdate=2023-04-18]", payment.toString());
    }
}
