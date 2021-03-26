package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.ProductEntity;
import com.demo.model.ResponseDataModel;
import com.demo.service.ProductService;

@RestController
@RequestMapping("/api")
@Profile("dev")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/list")
	public List<ProductEntity> getList() {

		return productService.getAllProduct();
	}

	@GetMapping("/product/find/{id}")
	public ProductEntity findByProductId(@PathVariable(name = "id") Integer productId) {
		return productService.findByProductId(productId);

	}

	@PostMapping("/product/add")
	public ResponseDataModel addProduct(ProductEntity productEntity) {
		return productService.addProduct(productEntity);
	}

	@PutMapping("/product/update")
	public ResponseDataModel editProduct(ProductEntity productEntity) {
		return productService.updateProduct(productEntity);
	}

	@DeleteMapping("/product/delete/{id}")
	public ResponseDataModel deleteProduct(@PathVariable(name = "id") Integer productId) {
		return productService.deleteProduct(productId);
	}
}
