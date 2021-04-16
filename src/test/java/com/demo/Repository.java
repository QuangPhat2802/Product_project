package com.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.dao.ProductReponsitory;
import com.demo.entity.ProductEntity;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class Repository {

	@Autowired
	private ProductReponsitory productrepo;

	@Test
	public void testSaveProduct(){
		ProductEntity product = new ProductEntity();
		product.setProductName("phat01");
		product.setDescription("phat01");
		product.setPrice(123);
		product.setQuantity(123);
		productrepo.save(product);
//		ProductEntity product2 = productrepo.findByProductId(14);
//		assertNull(product);
		Assert.assertNotNull(product.getProductId());
		
	}

}
