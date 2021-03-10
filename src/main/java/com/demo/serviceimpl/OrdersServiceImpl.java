package com.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.OrdersReponsitory;
import com.demo.dao.ProductReponsitory;
import com.demo.entity.OrdersDetailsEntity;
import com.demo.exception.ProductTransactionException;
import com.demo.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersReponsitory ordersRepo;

	@Autowired
	ProductReponsitory productRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ProductTransactionException.class)
	public OrdersDetailsEntity addItems(OrdersDetailsEntity ordersDetailsEntity) throws ProductTransactionException {
		if(ordersDetailsEntity.getQuantity() < 0) {
			throw new ProductTransactionException("quantity Should not be negative");
		}
		return ordersRepo.saveAndFlush(ordersDetailsEntity);
	}

}
