package com.homecommerce.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartDTOTest {

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
