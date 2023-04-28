package com.homecommerce.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.homecommerce.dtos.LoginDTO;
import com.homecommerce.dtos.Response;
import com.homecommerce.models.Customer;
import com.homecommerce.services.CustomerService;

public class CustomerControllerTest {
    /**
     This is a test class for the CustomerController class which handles HTTP requests and responses for the Customer model.
     The tests in this class ensure that the CustomerController class is working as intended.
     It uses the Mockito framework to mock the CustomerService dependency, and test the behavior of the CustomerController methods.
     The first test method, testSave(), verifies that the save() method in the CustomerController class saves a customer using the CustomerService, and returns an HTTP status of OK.
     The second test method, testFindAllCustomers(), verifies that the findAllCustomers() method in the CustomerController class retrieves all customers using the CustomerService, and returns a list of customers.
     The third test method, testFindCustomerById(), verifies that the findCustomerById() method in the CustomerController class retrieves a customer by id using the CustomerService, and returns the customer as a response with a success message.
     The fourth test method, testValidateUser(), verifies that the validateUser() method in the CustomerController class validates a user using the CustomerService, and returns the validated customer as a response with a success message.
     The fifth test method, testUpdateProfile(), verifies that the updateProfile() method in the CustomerController class updates a customer's profile using the CustomerService, and returns an HTTP status of OK.
     */

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        Customer customer = new Customer();
        doNothing().when(customerService).registerCustomer(customer);
        ResponseEntity<?> response = customerController.save(customer);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindAllCustomers() {
        Customer customer = new Customer();
        when(customerService.allCustomers()).thenReturn(Arrays.asList(customer));
        List<Customer> result = customerController.findAllCustomers();
        Assertions.assertEquals(Arrays.asList(customer), result);
    }

    @Test
    public void testFindCustomerById() {
        Customer customer = new Customer();
        when(customerService.findById(anyInt())).thenReturn(customer);
        ResponseEntity<?> response = customerController.findCustomerById(1);
        Assertions.assertEquals(Response.success(customer).getBody(), response.getBody());
    }

    @Test
    public void testValidateUser() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserid("test");
        loginDTO.setPwd("test");
        Customer customer = new Customer();
        customer.setUserid("test");
        customer.setPwd("test");
        when(customerService.validate("test", "test")).thenReturn(customer);
        ResponseEntity<?> response = customerController.validateUser(loginDTO);
        Assertions.assertEquals(Response.success(customer).getBody(), response.getBody());
    }

    @Test
    public void testUpdateProfile() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setPwd("test");
        when(customerService.findById(1)).thenReturn(customer);
        doNothing().when(customerService).updateProfile(customer);
        ResponseEntity<?> response = customerController.updateProfile(customer, 1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void testValidateUser_withInvalidCredentials() {
        // Arrange
        LoginDTO dto = new LoginDTO();
        dto.setUserid("john123");
        dto.setPwd("wrongpassword");
        when(customerService.validate(dto.getUserid(), dto.getPwd())).thenReturn(null);

        ResponseEntity<?> response = customerController.validateUser(dto);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
