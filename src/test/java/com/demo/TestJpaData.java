package com.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.dao.ProductReponsitory;
import com.demo.entity.ProductEntity;
import com.demo.service.ProductService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestJpaData {

	@Autowired
	private ProductReponsitory productRepo;

//	@MockBean
//	private ProductService productService;

	@Test
	public void testSaveProduct() {
		ProductEntity product = new ProductEntity();
//		product.setProductId(15);
		product.setProductName("phat01");
		product.setDescription("phat01");
		product.setPrice(123);
		product.setQuantity(123);
		ProductEntity productSave = productRepo.save(product);
		assertNotNull(productSave);

	}

	@Test
	public void testFindProductByProductId() {
		int productId = 14;
		ProductEntity product = productRepo.findByProductId(productId);
//		assertThat(product.getProductId()).isEqualTo(productId);
		assertNotNull(product);
	}

}
