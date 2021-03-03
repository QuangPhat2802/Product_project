package com.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ProductReponsitory;
import com.demo.entity.ProductEntity;
import com.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductReponsitory productRepo;
	
	@Override
	public List<ProductEntity> getAllProduct() {
		return productRepo.findAll() ;
	}
//	@Override
//	public ResponseEntity<List<ProductEntity>> getAllProduct() {
//		List<ProductEntity> listProduct = productRepo.findAll();
//		if(listProduct.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return  new ResponseEntity<List<ProductEntity>>(HttpStatus.OK);
//	}

	@Override
	public ProductEntity addProduct(ProductEntity productEntity) {
	
		return productRepo.saveAndFlush(productEntity) ;
	}

	@Override
	public ProductEntity updateProduct(ProductEntity productEntity) {
		// TODO Auto-generated method stub
		return productRepo.saveAndFlush(productEntity);
	}

	@Override
	public ProductEntity deleteProduct(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductEntity findByProductId(int productId) {
		return productRepo.findByProductId(productId);
	}

	
	
}
