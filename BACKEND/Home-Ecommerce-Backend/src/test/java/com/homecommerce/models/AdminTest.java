package com.homecommerce.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminTest {
    /**
     * This class contains unit tests for the Admin class.
     * The testGettersAndSetters() method tests the getters and setters of the Admin class.
     * The testConstructor() method tests the constructor of the Admin class.
     * The testToString() method tests the toString() method of the Admin class.
     */

    @Test
    public void testGettersAndSetters() {
        Admin admin = new Admin();
        admin.setUserid("admin");
        admin.setPwd("password");
        admin.setUname("Admin User");

        Assertions.assertEquals("admin", admin.getUserid());
        Assertions.assertEquals("password", admin.getPwd());
        Assertions.assertEquals("Admin User", admin.getUname());
    }

    @Test
    public void testConstructor() {
        Admin admin = new Admin("admin", "password", "Admin User");

        Assertions.assertEquals("admin", admin.getUserid());
        Assertions.assertEquals("password", admin.getPwd());
        Assertions.assertEquals("Admin User", admin.getUname());
    }

    @Test
    public void testToString() {
        Admin admin = new Admin("admin", "password", "Admin User");

        String expected = "Admin [userid=admin, pwd=password, uname=Admin User]";
        String actual = admin.toString();

        Assertions.assertEquals(expected, actual);
    }
}
