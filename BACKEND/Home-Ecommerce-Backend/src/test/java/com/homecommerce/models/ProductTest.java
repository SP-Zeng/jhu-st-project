package com.homecommerce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

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
