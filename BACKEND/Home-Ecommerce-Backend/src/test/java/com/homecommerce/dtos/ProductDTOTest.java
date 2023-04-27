package com.homecommerce.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.homecommerce.models.Product;

public class ProductDTOTest {

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
