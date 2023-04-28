package com.homecommerce.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WishlistTest {
    /**

     This is a test class for the Wishlist model. It contains test cases for the
     setId(), getId(), setCustomer(), getCustomer(), setProduct(), getProduct(), and toString()
     methods of the Wishlist class.
     The tests verify that the Wishlist object can be created and that its attributes can be set
     and retrieved correctly. The toString() method is also tested to ensure that it returns the
     expected string representation of the Wishlist object.
     */

    @Test
    void testSetAndGetId() {
        Wishlist wishlist = new Wishlist();
        wishlist.setId(1);
        assertEquals(1, wishlist.getId());
    }

    @Test
    void testSetAndGetCustomer() {
        Wishlist wishlist = new Wishlist();
        Customer customer = new Customer(1);
        wishlist.setCustomer(customer);
        assertEquals(customer, wishlist.getCustomer());
    }

    @Test
    void testSetAndGetProduct() {
        Wishlist wishlist = new Wishlist();
        Product product = new Product(1);
        wishlist.setProduct(product);
        assertEquals(product, wishlist.getProduct());
    }

    @Test
    void testToString() {
        Wishlist wishlist = new Wishlist();
        wishlist.setId(1);
        Customer customer = new Customer(1);
        wishlist.setCustomer(customer);
        Product product = new Product(1);
        wishlist.setProduct(product);
        assertEquals("Wishlist [id=1, customer=" + customer + ", product=" + product + "]", wishlist.toString());
    }

}
