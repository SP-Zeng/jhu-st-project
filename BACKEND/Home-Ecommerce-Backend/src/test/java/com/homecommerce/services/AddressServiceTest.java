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
