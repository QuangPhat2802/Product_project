package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.ProductEntity;
import com.demo.exception.RecordNotFoundException;
import com.demo.model.ResponseDataModel;
import com.demo.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/list")
	public List<ProductEntity> getList() {

		return productService.getAllProduct();
	}

	@GetMapping("/product/find/{id}")
	public ResponseDataModel findByProductId(@PathVariable(name = "id") Integer productId)
			throws Exception {

		ProductEntity findById = productService.findByProductId(productId);
		if (findById == null) {
			throw new RecordNotFoundException("Invalid product ID: " + productId);
		}
		return new ResponseDataModel(200, "success",findById);
	}

	@PostMapping("/product/add")
	public ResponseDataModel addProduct(@Valid @RequestBody ProductEntity productEntity) {
		return productService.addProduct(productEntity);
	}

	/**
	 * Exception Handler : SOLUTION 2
	 * 
	 * @param productEntity
	 * @return
	 */
//
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//		Map<String, String> errors = new HashMap<String, String>();
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName = ((FieldError) error).getField();
//			String errorMessage = error.getDefaultMessage();
//			errors.put(fieldName, errorMessage);
//		});
////		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
////			String fieldName = ((FieldError) error).getField();
////			String errorMessage = error.getDefaultMessage();
////			errors.put(fieldName, errorMessage);
////		}
//		return errors;
//	}

	@PutMapping("/product/update")
	public ResponseDataModel editProduct(ProductEntity productEntity) {
		return productService.updateProduct(productEntity);
	}

	@DeleteMapping("/product/delete/{id}")
	public ResponseDataModel deleteProduct(@PathVariable(name = "id") Integer productId) {
		return productService.deleteProduct(productId);
	}
}
