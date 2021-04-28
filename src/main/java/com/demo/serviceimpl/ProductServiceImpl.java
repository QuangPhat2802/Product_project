package com.demo.serviceimpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ProductReponsitory;
import com.demo.entity.ProductEntity;
import com.demo.model.ResponseDataModel;
import com.demo.service.ProductService;

@Service

public class ProductServiceImpl implements ProductService {

	public static final int ResponseCode_fail = 0;
	public static final int ResponseCode_duplicate = 1;
	public static final int ResponseCode_success = 200;
	@Autowired
	ProductReponsitory productRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProductEntity> getAllProduct() {
		return productRepo.findAll();
	}

	@Override
	public ResponseDataModel addProduct(ProductEntity productEntity) {

		ProductEntity product = productRepo.saveAndFlush(productEntity);
		return new ResponseDataModel(ResponseCode_success, "success", product);

	}

	@Override
	public ResponseDataModel updateProduct(ProductEntity productEntity) {
		ProductEntity product = productRepo.saveAndFlush(productEntity);
		return new ResponseDataModel(ResponseCode_success, "success", product);
	}

	@Override
	public ResponseDataModel deleteProduct(int productId) {
		int responseCode = ResponseCode_fail;
		String responseMessage = StringUtils.EMPTY;
		ProductEntity product = productRepo.findByProductId(productId);
		if (product != null) {
			productRepo.deleteById(productId);
			responseCode = ResponseCode_success;
			responseMessage = "delete is success";
		}
		return new ResponseDataModel(responseCode, responseMessage);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ProductEntity findByProductId(int productId) {
		return productRepo.findByProductId(productId);
	}

}
