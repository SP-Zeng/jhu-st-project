package com.homecommerce.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartDTOTest {
    /**
     The CartDTOTest class contains a unit test for the CartDTO class.
     The CartDTO class is a data transfer object that represents a shopping cart item.
     It has properties such as ID, category, book name, price, and quantity.
     The CartDTOTest class tests the getters and setters of the CartDTO class
     by setting values for each property and then asserting that the getter
     methods return the expected values.
     This test is designed to ensure that the CartDTO class is correctly
     implemented and that its properties can be set and retrieved correctly.
     If any of the assertions fail, it indicates that there is a problem
     with the CartDTO class implementation.
     */

    @Test
    public void testGettersAndSetters() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(1);
        cartDTO.setCat("Books");
        cartDTO.setBname("The Great Gatsby");
        cartDTO.setPrice(10);
        cartDTO.setQty(2);

        assertEquals(1, cartDTO.getId());
        assertEquals("Books", cartDTO.getCat());
        assertEquals("The Great Gatsby", cartDTO.getBname());
        assertEquals(10, cartDTO.getPrice());
        assertEquals(2, cartDTO.getQty());
    }
}
