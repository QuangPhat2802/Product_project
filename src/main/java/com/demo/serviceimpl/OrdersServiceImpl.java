package com.demo.serviceimpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.BillReponsitory;
import com.demo.dao.CustomerReponsitory;
import com.demo.dao.OrdersReponsitory;
import com.demo.dao.ProductReponsitory;
import com.demo.entity.OrdersDetailsEntity;
import com.demo.entity.OrdersEntity;
import com.demo.entity.ProductEntity;
import com.demo.exception.ProductTransactionException;
import com.demo.model.ResponseDataModel;
import com.demo.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersReponsitory ordersRepo;

	@Autowired
	ProductReponsitory productRepo;

	@Autowired
	CustomerReponsitory customerRepo;

	@Autowired
	BillReponsitory billRepo;

	@Override
	public OrdersDetailsEntity addItems(OrdersDetailsEntity ordersDetailsEntity) throws ProductTransactionException {

		ProductEntity productEntity = productRepo
				.findByProductId(ordersDetailsEntity.getProductEntity().getProductId());
		if (ordersDetailsEntity.getQuantity() > productEntity.getQuantity()) {
			throw new ProductTransactionException("dont add");
		} else {
			productEntity.setQuantity(productEntity.getQuantity() - ordersDetailsEntity.getQuantity());
			ordersDetailsEntity.setPrice(productEntity.getPrice() * ordersDetailsEntity.getQuantity());
		}
		return ordersRepo.saveAndFlush(ordersDetailsEntity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ResponseDataModel confirmOrders(OrdersEntity ordersEntity) {
		int responseCode = 0;
		String responseMessage = StringUtils.EMPTY;
		List<OrdersDetailsEntity> list = ordersRepo.findByCustomerId(ordersEntity.getCustomerEntity().getCustomerId());
		int totalPrice = 0;
		for (OrdersDetailsEntity i : list) {
			totalPrice += i.getPrice();
			System.out.println(i.getPrice());
		}
		ordersEntity.setTotalPrice(totalPrice);
		try {
			OrdersEntity data = billRepo.saveAndFlush(ordersEntity);
			responseCode = 100;
			responseMessage = "success";
		} catch (Exception e) {
			e.getMessage();
		}
		return new ResponseDataModel(responseCode, responseMessage);
	}
}
