package com.demo;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.demo.controller.ProductController;
import com.demo.dao.ProductReponsitory;
import com.demo.entity.ProductEntity;
import com.demo.model.ResponseDataModel;
import com.demo.service.ProductService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class ControllerTest {

//	@InjectMocks
//	ProductController productController;

	@MockBean
	ProductService productService;

	@Autowired
	ProductReponsitory productRepo;

	@BeforeEach
	public void setUp() {

	}

	@Test
	public void testAddProduct() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		ProductEntity product = new ProductEntity();
		product.setProductId(15);
		product.setProductName("phat01");
		product.setDescription("phat011");
		product.setPrice(123);
		product.setQuantity(123);

		when(productService.addProduct((ProductEntity) any(ProductEntity.class)))
				.thenReturn(new ResponseDataModel(100, "complete"));

	}

}
