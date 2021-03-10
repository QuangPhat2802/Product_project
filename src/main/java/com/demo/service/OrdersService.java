package com.demo.service;

import com.demo.entity.OrdersDetailsEntity;
import com.demo.exception.ProductTransactionException;

public interface OrdersService {

	OrdersDetailsEntity addItems(OrdersDetailsEntity ordersEntity) throws ProductTransactionException;
}
	