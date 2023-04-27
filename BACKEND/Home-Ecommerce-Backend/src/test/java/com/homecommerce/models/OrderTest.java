package com.homecommerce.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
    }

    @Test
    public void testSetAndGetOrderId() {
        order.setOrderid(1);
        assertEquals(1, order.getOrderid());
    }

    @Test
    public void testSetAndGetOrderDate() {
        LocalDate orderDate = LocalDate.now();
        order.setOrderDate(orderDate);
        assertEquals(orderDate, order.getOrderDate());
    }

    @Test
    public void testSetAndGetCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        order.setCustomer(customer);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    public void testSetAndGetAddress() {
        Address address = new Address();
        address.setId(1);
        order.setAddress(address);
        assertEquals(address, order.getAddress());
    }

    @Test
    public void testSetAndGetPayment() {
        Payment payment = new Payment();
        payment.setId(1);
        order.setPayment(payment);
        assertEquals(payment, order.getPayment());
    }

    @Test
    public void testSetAndGetPaymethod() {
        order.setPaymethod("By Card");
        assertEquals("By Card", order.getPaymethod());
    }

    @Test
    public void testSetAndGetStatus() {
        order.setStatus("Pending");
        assertEquals("Pending", order.getStatus());
    }

    @Test
    public void testSetAndGetOrderDetails() {
        OrderDetails orderDetails = new OrderDetails();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails);
        order.setOrderdetails(orderDetailsList);
        assertEquals(orderDetailsList, order.getOrderdetails());
    }

    @Test
    public void testConstructor() {
        assertNotNull(order.getOrderDate());
        assertEquals("Pending", order.getStatus());
        assertEquals("By Card", order.getPaymethod());
    }
}
