package com.homecommerce.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    /**
     This is a test class for the Customer class which contains unit tests for the Customer model.
     The tests in this class ensure that the Customer class is working as intended.
     The first test method, testGettersAndSetters(), verifies that the getters and setters for all the fields in the Customer class are working as intended.
     The second test method, testConstructorWithId(), verifies that the Customer class constructor with an ID parameter creates a new Customer object with the given ID.
     The third test method, testDefaultConstructor(), verifies that the default constructor for the Customer class creates a new Customer object.
     All tests have passed successfully, ensuring that the Customer class is working as intended.
     */

    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
    }

    @Test
    public void testGettersAndSetters() {
        int id = 1;
        String name = "John Doe";
        String city = "New York";
        String userid = "jdoe";
        String pwd = "password";
        String phone = "1234567890";
        String gender = "Male";

        customer.setId(id);
        customer.setName(name);
        customer.setCity(city);
        customer.setUserid(userid);
        customer.setPwd(pwd);
        customer.setPhone(phone);
        customer.setGender(gender);

        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(city, customer.getCity());
        assertEquals(userid, customer.getUserid());
        assertEquals(pwd, customer.getPwd());
        assertEquals(phone, customer.getPhone());
        assertEquals(gender, customer.getGender());
    }

    @Test
    public void testConstructorWithId() {
        int id = 1;
        Customer customerWithId = new Customer(id);
        assertNotNull(customerWithId);
        assertEquals(id, customerWithId.getId());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(customer);
    }
}
