package com.homecommerce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void testGettersAndSetters() {
        Address address = new Address();
        address.setId(1);
        address.setCity("San Francisco");
        address.setState("CA");
        address.setZip("94102");
        address.setCountry("USA");

        Assertions.assertEquals(1, address.getId());
        Assertions.assertEquals("San Francisco", address.getCity());
        Assertions.assertEquals("CA", address.getState());
        Assertions.assertEquals("94102", address.getZip());
        Assertions.assertEquals("USA", address.getCountry());
    }

    @Test
    public void testToString() {
        Address address = new Address();
        address.setId(1);
        address.setCity("San Francisco");
        address.setState("CA");
        address.setZip("94102");
        address.setCountry("USA");

        String expected = "Address [id=1, city=San Francisco, state=CA, zip=94102, country=USA]";
        String actual = address.toString();

        Assertions.assertEquals(expected, actual);
    }
}
