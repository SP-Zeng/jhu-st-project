package com.homecommerce.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.homecommerce.models.Product;

public class ProductDTOTest {
	/**
	 This is a test class for the ProductDTO class, which is used to represent a product in the application.
	 It contains tests to ensure that the methods in the ProductDTO class are working as intended.
	 The first test method, testSetAndGetPic(), verifies that the setPic() and getPic() methods in the ProductDTO class
	 set and retrieve the image file of a product.
	 The second test method, testToEntity(), verifies that the toEntity() method in the ProductDTO class correctly converts
	 a ProductDTO object into a Product entity.
	 This class is important to maintain code quality and ensure that the ProductDTO class behaves as expected when used
	 in the application.
	 */

	@Test
	public void testSetAndGetPic() {
		MockMultipartFile pic = new MockMultipartFile("file", "filename.jpg", "image/jpeg", new byte[10]);
		ProductDTO dto = new ProductDTO();
		dto.setPic(pic);
		assertEquals(pic, dto.getPic());
	}

	@Test
	public void testToEntity() {
		ProductDTO dto = new ProductDTO();
		dto.setId(1);
		dto.setPname("test product");
		dto.setDescr("test description");
		dto.setPrice(100);
		dto.setPhoto("test.jpg");
		Product entity = ProductDTO.toEntity(dto);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getPname(), entity.getPname());
		assertEquals(dto.getDescr(), entity.getDescr());
		assertEquals(dto.getPrice(), entity.getPrice());
		assertEquals(dto.getPhoto(), entity.getPhoto());
	}
}
