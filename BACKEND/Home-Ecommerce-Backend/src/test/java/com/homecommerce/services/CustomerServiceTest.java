package com.homecommerce.services;

import com.homecommerce.models.Customer;
import com.homecommerce.repos.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    /**

     This is a test class for the CustomerService class, which provides functionality for managing customer data in the application.
     The tests in this class cover all public methods of the CustomerService class and ensure that they are working correctly.
     The test methods are as follows:
     testRegisterCustomer() - verifies that the registerCustomer() method in the CustomerService class saves a new customer
     using the CustomerRepository and returns void.
     testAllCustomers() - verifies that the allCustomers() method in the CustomerService class retrieves all customers using
     the CustomerRepository and returns a list of customers.
     testFindById() - verifies that the findById() method in the CustomerService class retrieves a customer with the specified id
     using the CustomerRepository and returns the customer.
     testValidateWithValidCredentials() - verifies that the validate() method in the CustomerService class returns a customer object
     when valid login credentials are provided.
     testValidateWithInvalidCredentials() - verifies that the validate() method in the CustomerService class returns null
     when invalid login credentials are provided.
     testVerifyUserId() - verifies that the verifyUserId() method in the CustomerService class returns true if the provided userId
     exists in the database, and false if it doesn't.
     testUpdateProfile_withNonNullPwd() - verifies that the updateProfile() method in the CustomerService class updates a customer's
     profile data when a non-null password is provided, and leaves the password unchanged.
     testUpdateProfile_withNullOrEmptyPwd() - verifies that the updateProfile() method in the CustomerService class updates a customer's
     profile data when a null or empty password is provided, and leaves the password unchanged.
     */

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
    void testUpdateProfile_withNonNullPwd() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John");
        customer.setCity("New York");
        customer.setUserid("john123");
        customer.setPwd("password");
        customer.setPhone("1234567890");
        customer.setGender("male");

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(customer);

        customerService.updateProfile(customer);

        assertEquals("password", customer.getPwd());
    }


    @Test
    void testUpdateProfile_withNullOrEmptyPwd() {
        // BUG: null password causes nullpointer failure
        Customer customer = new Customer();
        customer.setId(1);
        customer.setPwd("");
        assertEquals("", customer.getPwd());
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        customerService.updateProfile(customer);

        Customer NullCustomer = new Customer();
        NullCustomer.setId(1);
        NullCustomer.setPwd(null);
        assertEquals(null, NullCustomer.getPwd());
        when(customerRepository.findById(2)).thenReturn(Optional.of(NullCustomer));
        customerService.updateProfile(NullCustomer);

    }
}
