package com.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.dao.ProductReponsitory;
import com.demo.entity.ProductEntity;
import com.demo.service.ProductService;
import com.demo.serviceimpl.ProductServiceImpl;


@ExtendWith(SpringExtension.class)
public class ProductServiceImplIntegrationTest {

	 @TestConfiguration
	    static class  ProductServiceImplTestContextConfiguration {
	        @Bean
	        public ProductService productService() {
	            return new ProductServiceImpl();
	        }
	    }
	
	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductReponsitory productRepo;
	
	@Test
	public void setUp() {
		ProductEntity product = new ProductEntity("phat01", 1, "phat01", 2);
		product.setProductId(1);
		List<ProductEntity> list = Arrays.asList(product);
		
		Mockito.when(productRepo.findById(product.getProductId())).thenReturn(Optional.of(product));
		Mockito.when(productRepo.findAll()).thenReturn(list);
	}
}
