package com.homecommerce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

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
