package com.demo.service;

import java.util.List;

import com.demo.entity.ProductEntity;
import com.demo.model.ResponseDataModel;

public interface ProductService {

	List<ProductEntity> getAllProduct();

	ResponseDataModel addProduct(ProductEntity productEntity);

	ResponseDataModel updateProduct(ProductEntity productEntity);

	ResponseDataModel deleteProduct(int productId);

	ProductEntity findByProductId(int productId);

}
