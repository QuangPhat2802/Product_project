package com.demo;

import static org.mockito.Mockito.mockitoSession;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.controller.ProductController;
import com.demo.dao.ProductReponsitory;
import com.demo.entity.ProductEntity;
import com.demo.entity.UsersEntity;
import com.demo.jwt.JsonTokenProvider;
import com.demo.model.ResponseDataModel;
import com.demo.secutiry.CustomUserDetails;
import com.demo.serviceimpl.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
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

	private UsernamePasswordAuthenticationToken authentication;

	@Autowired
	private AuthenticationManager authMagager;

	public void before() {
		this.authentication = new UsernamePasswordAuthenticationToken("admin", "admin");
		SecurityContextHolder.getContext().setAuthentication(authMagager.authenticate(authentication));
	}

	/**
	 * 
	 * @throws Exception
	 */
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

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddProduct() throws Exception {
		productEntity = new ProductEntity("phat1", 10000, "phat", 20000);
		Mockito.when(productService.addProduct(productEntity)).thenReturn(new ResponseDataModel(200, "success"));
		String jsonRequest = objectMapper.writeValueAsString(productEntity);
		MvcResult result = mvc.perform(post("/api/product/add").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		ResponseDataModel response = objectMapper.readValue(resultContent, ResponseDataModel.class);
		Assert.assertEquals(200, response.getResponseCode());
	}

	@Test
	public void shouldNotAllowAccessToUnAuthenticatedUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/login")).andExpect(status().isForbidden());
	}

	@Test
	public void shouldGenegateAuthToken(@Autowired JsonTokenProvider json) throws Exception {
		UsersEntity user = new UsersEntity("admin", "admin");
		CustomUserDetails customer = new CustomUserDetails(user);
		String token = json.generateToken(customer);
//		mvc.perform(MockMvcRequestBuilders.post("/api/login").header("Authorization", token)).andExpect(status().isOk())
//				.andDo(print()).andDo(print());
		mvc.perform(MockMvcRequestBuilders.post("/api/login").header("Authorization", json.getUserNameFromToken(token)))
				.andExpect(status().isOk()).andDo(print()).andDo(print());
		mvc.perform(MockMvcRequestBuilders.post("/api/login").header("Authorization", token)).andExpect(status().isOk())
				.andDo(print()).andDo(print());
	}

	@Test
	public void testUpdateProduct() throws JsonProcessingException, Exception {
		productEntity1 = new ProductEntity("phat1", 10000, "phat", 20000);
		Mockito.when(productService.updateProduct(productEntity1)).thenReturn(new ResponseDataModel(200, "success"));
		mvc.perform(put("/api/product/update").content(objectMapper.writeValueAsString(productEntity1))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

//	@Test
//	public void testDeleteProduct() throws Exception {
//		Integer productID = 1;
////		Mockito.when(productService.deleteProduct(productID)).thenReturn(new ResponseDataModel(100, "success"));
//		Mockito.doNothing().when(productService.deleteProduct(productID));
//		String url = "/api/product/delete/" + productID;
//		mvc.perform(delete(url)).andExpect(status().isOk());
//
//	}
}
