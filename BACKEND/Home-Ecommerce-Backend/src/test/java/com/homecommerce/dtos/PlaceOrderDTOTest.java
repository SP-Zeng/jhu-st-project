package com.homecommerce.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.homecommerce.models.Address;
import com.homecommerce.models.Payment;

public class PlaceOrderDTOTest {
	/**
	 This is a test class for the PlaceOrderDTO class which is a data transfer object for orders placed by customers.
	 The tests in this class ensure that the getters and setters for the attributes in the PlaceOrderDTO class work correctly.
	 The first test method, testGetSetPaymethod(), verifies that the setPaymethod() method in the PlaceOrderDTO class sets the payment method and the getPaymethod() method retrieves it.
	 The second test method, testGetSetCustomerid(), verifies that the setCustomerid() method in the PlaceOrderDTO class sets the customer id and the getCustomerid() method retrieves it.
	 The third test method, testGetSetAddress(), verifies that the setAddress() method in the PlaceOrderDTO class sets the address and the getAddress() method retrieves it.
	 The fourth test method, testGetSetPayment(), verifies that the setPayment() method in the PlaceOrderDTO class sets the payment and the getPayment() method retrieves it.
	 */

	@Test
	public void testGetSetPaymethod() {
		PlaceOrderDTO dto = new PlaceOrderDTO();
		String paymethod = "credit";
		dto.setPaymethod(paymethod);
		assertEquals(paymethod, dto.getPaymethod());
	}

	@Test
	public void testGetSetCustomerid() {
		PlaceOrderDTO dto = new PlaceOrderDTO();
		int customerid = 123;
		dto.setCustomerid(customerid);
		assertEquals(customerid, dto.getCustomerid());
	}

	@Test
	public void testGetSetAddress() {
		PlaceOrderDTO dto = new PlaceOrderDTO();
		Address address = new Address();
		dto.setAddress(address);
		assertEquals(address, dto.getAddress());
	}

	@Test
	public void testGetSetPayment() {
		PlaceOrderDTO dto = new PlaceOrderDTO();
		Payment payment = new Payment();
		dto.setPayment(payment);
		assertEquals(payment, dto.getPayment());
	}

}
