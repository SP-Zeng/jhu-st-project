package com.homecommerce.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import com.homecommerce.models.OrderDetails;
import com.homecommerce.models.Product;

public class OrderDetailsDTOTest {
	/**
	 This is a test class for the OrderDetailsDTO class which tests the getters, setters, and the fromEntity() method.
	 The fromEntity() method is tested by mocking an OrderDetails entity and setting its properties. The DTO's fromEntity()
	 method is then called and the returned DTO is checked for its property values against the mocked entity.
	 */

	private OrderDetailsDTO orderDetailsDTO;

	@BeforeEach
	public void setup() {
		orderDetailsDTO = new OrderDetailsDTO();
	}

	@Test
	public void testSetAndGetId() {
		int id = 123;
		orderDetailsDTO.setId(id);
		assertEquals(id, orderDetailsDTO.getId());
	}

	@Test
	public void testSetAndGetProduct() {
		Product product = mock(Product.class);
		orderDetailsDTO.setProduct(product);
		assertEquals(product, orderDetailsDTO.getProduct());
	}

	@Test
	public void testSetAndGetQty() {
		int qty = 2;
		orderDetailsDTO.setQty(qty);
		assertEquals(qty, orderDetailsDTO.getQty());
	}

	@Test
	public void testFromEntity() {
		OrderDetails entity = mock(OrderDetails.class);
		int id = 123;
		Product product = mock(Product.class);
		int qty = 2;

		when(entity.getId()).thenReturn(id);
		when(entity.getProduct()).thenReturn(product);
		when(entity.getQty()).thenReturn(qty);

		OrderDetailsDTO dto = OrderDetailsDTO.fromEntity(entity);

		assertNotNull(dto);
		assertEquals(id, dto.getId());
		assertEquals(product, dto.getProduct());
		assertEquals(qty, dto.getQty());
	}

}
