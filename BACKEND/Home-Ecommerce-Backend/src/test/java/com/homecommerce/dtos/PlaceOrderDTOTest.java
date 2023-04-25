package com.homecommerce.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.homecommerce.models.Address;
import com.homecommerce.models.Payment;

public class PlaceOrderDTOTest {

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
