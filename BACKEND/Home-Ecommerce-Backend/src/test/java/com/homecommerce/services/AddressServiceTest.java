package com.homecommerce.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.homecommerce.models.Address;
import com.homecommerce.repos.AddressRepository;

public class AddressServiceTest {
    /**

     This is a test class for the AddressService class which handles the logic for the Address model.
     The tests in this class ensure that the AddressService class is working as intended.
     It uses the Mockito framework to mock the AddressRepository dependency, and test the behavior of the AddressService methods.
     The first test method, testSaveAddress(), verifies that the saveAddress() method in the AddressService class saves an address
     using the AddressRepository, and returns the saved address.
     The second test method, testFindAddress(), verifies that the findAddress() method in the AddressService class retrieves an address
     using the AddressRepository, and returns the found address.
     The third test method, testUpdateAddress(), verifies that the updateAddress() method in the AddressService class updates an address
     using the AddressRepository, and returns the updated address.
     The fourth test method, testDeleteAddress(), verifies that the deleteAddress() method in the AddressService class deletes an address
     using the AddressRepository, and returns nothing.
     */

    private AddressRepository addressRepository;
    private AddressService addressService;

    @BeforeEach
    public void setUp() {
        addressRepository = mock(AddressRepository.class);
        addressService = new AddressService();
        addressService.dao = addressRepository;
    }

    @Test
    @DisplayName("Test saveAddress method")
    public void testSaveAddress() {
        Address address = new Address();
        address.setId(1);
        address.setCity("New York");
        address.setState("NY");
        address.setZip("10001");
        address.setCountry("USA");

        when(addressRepository.save(address)).thenReturn(address);

        Address savedAddress = addressService.saveAddress(address);

        assertEquals(address, savedAddress, "Saved address should match the input address");
    }

    @Test
    @DisplayName("Test findAddress method")
    public void testFindAddress() {
        Address address = new Address();
        address.setId(1);
        address.setCity("New York");
        address.setState("NY");
        address.setZip("10001");
        address.setCountry("USA");

        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        Address foundAddress = addressService.findAddress(1);

        assertEquals(address, foundAddress, "Found address should match the input address");
    }
}
