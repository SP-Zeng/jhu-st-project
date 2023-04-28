package com.homecommerce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    /**
     This class contains unit tests for the Category class, which represents a category of products in a home commerce application.
     The class tests the getters and setters of the Category class, as well as its constructors.
     The testGettersAndSetters() method tests the functionality of the setId(), setCatname(), getId(), and getCatname() methods.
     The testConstructor() method tests the functionality of the constructor that takes an ID parameter.
     The testEmptyConstructor() method tests the functionality of the empty constructor.
     These tests ensure that the Category class is properly implemented and can be used to store and retrieve information about product categories.
     */

    @Test
    public void testGettersAndSetters() {
        Category category = new Category();
        category.setId(1);
        category.setCatname("Books");

        Assertions.assertEquals(1, category.getId());
        Assertions.assertEquals("Books", category.getCatname());
    }

    @Test
    public void testConstructor() {
        Category category = new Category(1);

        Assertions.assertEquals(1, category.getId());
    }

    @Test
    public void testEmptyConstructor() {
        Category category = new Category();

        Assertions.assertEquals(0, category.getId());
        Assertions.assertNull(category.getCatname());
    }
}
