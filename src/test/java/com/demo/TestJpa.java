package com.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.controller.ProductController;
import com.demo.dao.ProductReponsitory;
import com.demo.entity.ProductEntity;
import com.demo.service.ProductService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class TestJpa {

	@Autowired
	private ProductReponsitory productrepo;

	@Autowired
	private ProductService productService;

	@SuppressWarnings("unused")
	@Test
	public void testSaveProduct() {
		ProductEntity product = new ProductEntity();
		product.setProductName("phat0");
		product.setDescription("phat1");
		product.setPrice(123456);
		product.setQuantity(123213);
		productrepo.save(product);
//		ProductEntity product2 = productrepo.findByProductId(14);
//		assertNull(product);
		Assert.assertNotNull(product.getProductId());
		ProductEntity product2 = productrepo.findByProductId(1);
//		Assert.assertEquals(product2.getProductId(), product.getProductId());
		Assertions.assertEquals(product2.getProductId(), product.getProductId());

	}

}
