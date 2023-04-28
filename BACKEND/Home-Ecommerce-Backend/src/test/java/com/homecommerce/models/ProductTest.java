package com.homecommerce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    /**

     This is a test class for the Product class which is a model class that represents a product in the home-commerce system.
     The tests in this class ensure that the Product class is working as intended.
     The first test method, testConstructorAndGetters(), verifies that the Product constructor and getters correctly set and return the properties of a product.
     The second test method, testSetters(), verifies that the setters correctly set the properties of a product.
     The third test method, testToString(), verifies that the toString() method in the Product class returns the expected string representation of a product.
     */

    @Test
    public void testConstructorAndGetters() {
        Category category = new Category(1);
        Product product = new Product(1);
        product.setPname("Test Product");
        product.setDescr("This is a test product.");
        product.setPrice(100);
        product.setPhoto("test-product.jpg");
        product.setCategory(category);
        Assertions.assertEquals(1, product.getId());
        Assertions.assertEquals("Test Product", product.getPname());
        Assertions.assertEquals("This is a test product.", product.getDescr());
        Assertions.assertEquals(100, product.getPrice());
        Assertions.assertEquals("test-product.jpg", product.getPhoto());
        Assertions.assertEquals(category, product.getCategory());
    }

    @Test
    public void testSetters() {
        Category category = new Category(1);
        Product product = new Product();
        product.setPname("Test Product");
        product.setDescr("This is a test product.");
        product.setPrice(100);
        product.setPhoto("test-product.jpg");
        product.setCategory(category);
        Assertions.assertEquals("Test Product", product.getPname());
        Assertions.assertEquals("This is a test product.", product.getDescr());
        Assertions.assertEquals(100, product.getPrice());
        Assertions.assertEquals("test-product.jpg", product.getPhoto());
        Assertions.assertEquals(category, product.getCategory());
    }

    @Test
    public void testToString() {
        Category category = new Category(1);
        Product product = new Product();
        product.setPname("Test Product");
        product.setDescr("This is a test product.");
        product.setPrice(100);
        product.setPhoto("test-product.jpg");
        product.setCategory(category);
        String expectedToString = "Product [id=0, pname=Test Product, descr=This is a test product., category=" + category.toString() + ", price=100, photo=test-product.jpg]";
        Assertions.assertEquals(expectedToString, product.toString());
    }
}
