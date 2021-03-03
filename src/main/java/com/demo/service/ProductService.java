package com.demo.service;

import java.util.List;

import com.demo.entity.ProductEntity;

public interface ProductService {

	List<ProductEntity> getAllProduct();
	
	ProductEntity addProduct(ProductEntity productEntity);
	
	ProductEntity updateProduct(ProductEntity productEntity);
	
	ProductEntity deleteProduct(int productId);
	
	ProductEntity findByProductId(int productId);
}
