package com.homecommerce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartTest {

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
