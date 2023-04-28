package com.homecommerce.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WishlistDTOTest {
    /**
     This class contains JUnit tests for the WishlistDTO class, which represents a data transfer object for a customer's wishlist.
     The testGetCustid() method tests the getter for the custid field, by creating a new WishlistDTO object, setting its custid field to 1,
     and then asserting that the getCustid() method returns 1 as expected.
     The testSetCustid() method tests the setter for the custid field, by creating a new WishlistDTO object, setting its custid field to 1,
     and then asserting that the getCustid() method returns 1 as expected.
     */

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
