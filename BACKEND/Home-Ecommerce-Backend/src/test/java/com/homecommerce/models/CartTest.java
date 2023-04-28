package com.homecommerce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartTest {
    /**

     The CartTest class contains unit tests for the Cart class, which represents a customer's shopping cart.
     This class contains three tests: testGettersAndSetters, testConstructor, and testToString.
     The testGettersAndSetters method tests the getters and setters of the Cart class by creating a new Cart object and
     setting its properties using the setters. It then uses assertions to ensure that the getters return the expected values.
     The testConstructor method tests the constructor of the Cart class by creating a new Cart object with no arguments,
     setting its properties using the setters, and using assertions to ensure that the getters return the expected values.
     The testToString method tests the toString method of the Cart class by creating a new Cart object, setting its properties
     using the setters, and using assertions to ensure that the toString method returns the expected string.
     These tests ensure that the Cart class is functioning correctly and that its properties are being set and returned
     correctly by its methods.
     */

    @Test
    public void testGettersAndSetters() {
        Cart cart = new Cart();
        Customer customer = new Customer();
        Product product = new Product();

        // NOTE: default id is 0 from constructor
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setQty(2);
        cart.setId(5);

        Assertions.assertEquals(5, cart.getId());
        Assertions.assertEquals(customer, cart.getCustomer());
        Assertions.assertEquals(product, cart.getProduct());
        Assertions.assertEquals(2, cart.getQty());
    }

    @Test
    public void testConstructor() {
        Customer customer = new Customer();
        Product product = new Product();

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setProduct(product);

        Assertions.assertEquals(0, cart.getId());
        Assertions.assertEquals(customer, cart.getCustomer());
        Assertions.assertEquals(product, cart.getProduct());
        Assertions.assertEquals(1, cart.getQty());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer();
        Product product = new Product();

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setQty(2);
        cart.setId(1);

        String expected = "Cart [id=1, customer=" + customer.toString() + ", product=" + product.toString() + ", qty=2]";
        String actual = cart.toString();

        Assertions.assertEquals(expected, actual);
    }
}
