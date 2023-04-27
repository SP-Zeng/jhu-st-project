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
}
