package com.demo;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.demo.controller.ProductController;
import com.demo.dao.ProductReponsitory;
import com.demo.entity.ProductEntity;
import com.demo.model.ResponseDataModel;
import com.demo.serviceimpl.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class ProductControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductServiceImpl productService;

	@InjectMocks
	private ProductController productController;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ProductReponsitory productRepo;

	private ProductEntity productEntity;

	private ProductEntity productEntity1;

	List<ProductEntity> list;

	@Test
	public void getAllProduct() throws Exception {
		productEntity = new ProductEntity("phat", 1, "phat", 2);
		productEntity1 = new ProductEntity("phat1", 12, "phat1", 12);
		list = new ArrayList<ProductEntity>();
		list.add(productEntity1);
		list.add(productEntity);

		Mockito.when(productService.getAllProduct()).thenReturn(list);
		System.out.println(productService.getAllProduct());
		String url = "/api/list";
		MvcResult result = mvc.perform(get(url)).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testAddProduct() throws Exception {
		productEntity = new ProductEntity("phat11", 1, "phat", 2);
		Mockito.when(productService.addProduct(productEntity)).thenReturn(new ResponseDataModel(200, "success"));
		String jsonRequest = objectMapper.writeValueAsString(productEntity);
		MvcResult result = mvc.perform(post("/api/product/add").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		ResponseDataModel response = objectMapper.readValue(resultContent, ResponseDataModel.class);
		Assert.assertEquals(200, response.getResponseCode());
	} 
}
