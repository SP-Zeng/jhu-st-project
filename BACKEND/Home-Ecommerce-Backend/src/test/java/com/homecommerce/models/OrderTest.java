package com.homecommerce.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {
    /**
     This is a test class for the Order model which contains tests to verify that the Order model is working as intended.
     The test methods use the JUnit5 testing framework to test the getters, setters and constructor of the Order class.
     The testSetAndGetOrderId() method verifies that the setId() and getId() methods of the Order class set and get the orderId as expected.
     The testSetAndGetOrderDate() method verifies that the setOrderDate() and getOrderDate() methods of the Order class set and get the orderDate as expected.
     The testSetAndGetCustomer() method verifies that the setCustomer() and getCustomer() methods of the Order class set and get the customer as expected.
     The testSetAndGetAddress() method verifies that the setAddress() and getAddress() methods of the Order class set and get the address as expected.
     The testSetAndGetPayment() method verifies that the setPayment() and getPayment() methods of the Order class set and get the payment as expected.
     The testSetAndGetPaymethod() method verifies that the setPaymethod() and getPaymethod() methods of the Order class set and get the paymethod as expected.
     The testSetAndGetStatus() method verifies that the setStatus() and getStatus() methods of the Order class set and get the status as expected.
     The testSetAndGetOrderDetails() method verifies that the setOrderdetails() and getOrderdetails() methods of the Order class set and get the orderdetails as expected.
     The testConstructor() method verifies that the default values of the Order class constructor are set as expected.
     */

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
