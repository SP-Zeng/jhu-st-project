package com.homecommerce.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WishlistDTOTest {

    @Test
    public void testGetCustid() {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setCustid(1);
        Assertions.assertEquals(1, wishlistDTO.getCustid());
    }

    @Test
    public void testSetCustid() {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setCustid(1);
        Assertions.assertEquals(1, wishlistDTO.getCustid());
    }

}
