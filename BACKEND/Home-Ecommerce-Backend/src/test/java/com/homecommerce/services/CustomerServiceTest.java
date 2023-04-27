package com.homecommerce.services;

import com.homecommerce.models.Customer;
import com.homecommerce.repos.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setCity("New York");
        customer.setUserid("johndoe");
        customer.setPwd("password");
        customer.setPhone("1234567890");
        customer.setGender("Male");

        when(customerRepository.save(customer)).thenReturn(customer);

        customerService.registerCustomer(customer);

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("John Doe");
        customer1.setCity("New York");
        customer1.setUserid("johndoe");
        customer1.setPwd("password");
        customer1.setPhone("1234567890");
        customer1.setGender("Male");
        customerList.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Jane Doe");
        customer2.setCity("Los Angeles");
        customer2.setUserid("janedoe");
        customer2.setPwd("password");
        customer2.setPhone("1234567890");
        customer2.setGender("Female");
        customerList.add(customer2);

        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> actualCustomers = customerService.allCustomers();

        assertEquals(customerList, actualCustomers);
    }

    @Test
    void testFindById() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setCity("New York");
        customer.setUserid("johndoe");
        customer.setPwd("password");
        customer.setPhone("1234567890");
        customer.setGender("Male");

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Customer actualCustomer = customerService.findById(1);

        assertEquals(customer, actualCustomer);
    }

    @Test
    void testValidateWithValidCredentials() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setCity("New York");
        customer.setUserid("johndoe");
        customer.setPwd("password");
        customer.setPhone("1234567890");
        customer.setGender("Male");

        when(customerRepository.findByUserid("johndoe")).thenReturn(customer);

        Customer actualCustomer = customerService.validate("johndoe", "password");

        assertEquals(customer, actualCustomer);
    }

    @Test
    void testValidateWithInvalidCredentials() {
        when(customerRepository.findByUserid("johndoe")).thenReturn(null);
        Customer actualCustomer = customerService.validate("johndoe", "password");
        assertNull(actualCustomer);
    }

    @Test
    void testVerifyUserId() {
        String existingUserId = "user1";
        String nonExistingUserId = "user2";

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Test User");
        customer.setCity("Test City");
        customer.setUserid(existingUserId);
        customer.setPwd("test123");
        customer.setPhone("1234567890");
        customer.setGender("M");

        when(customerRepository.findByUserid(existingUserId)).thenReturn(customer);
        when(customerRepository.findByUserid(nonExistingUserId)).thenReturn(null);

        boolean isExistingUserIdValid = customerService.verifyUserId(existingUserId);
        boolean isNonExistingUserIdValid = customerService.verifyUserId(nonExistingUserId);

        assertTrue(isExistingUserIdValid);
        assertFalse(isNonExistingUserIdValid);
    }


    @Test
    void testUpdateProfile() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setCity("New York");
        customer.setUserid("johndoe");
        customer.setPwd("password");
        customer.setPhone("1234567890");
        customer.setGender("Male");
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customer.setName("John Smith");
        customer.setCity("Los Angeles");

        customerService.updateProfile(customer);

        verify(customerRepository, times(1)).findById(1);
        verify(customerRepository, times(1)).save(any(Customer.class));
        assertEquals("John Smith", customer.getName());
        assertEquals("Los Angeles", customer.getCity());

        // Test updating customer without changing password
        customer.setName("Jane Doe");
        customer.setPwd("");
        customerService.updateProfile(customer);

        verify(customerRepository, times(2)).findById(1);
        verify(customerRepository, times(2)).save(any(Customer.class));
        assertEquals("Jane Doe", customer.getName());
        assertEquals("password", customer.getPwd());
    }
    @Test
    void testUpdateProfileWithEmptyPassword() {
        Customer existingCustomer = new Customer();
        existingCustomer.setId(1);
        existingCustomer.setName("John");
        existingCustomer.setCity("New York");
        existingCustomer.setUserid("john123");
        existingCustomer.setPwd("password");
        existingCustomer.setPhone("555-5555");
        existingCustomer.setGender("male");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(1);
        updatedCustomer.setName("John Doe");
        updatedCustomer.setCity("Los Angeles");
        updatedCustomer.setUserid("john123");
        updatedCustomer.setPwd("");
        updatedCustomer.setPhone("555-5555");
        updatedCustomer.setGender("male");

        when(customerService.findById(1)).thenReturn(existingCustomer);

        customerService.updateProfile(updatedCustomer);

        verify(existingCustomer, times(1)).setPwd("password");
        verify(customerRepository, times(1)).save(updatedCustomer);
    }
}
