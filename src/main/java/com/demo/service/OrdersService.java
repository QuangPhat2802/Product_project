package com.demo.service;

import com.demo.entity.OrdersDetailsEntity;
import com.demo.entity.OrdersEntity;
import com.demo.exception.ProductTransactionException;
import com.demo.model.ResponseDataModel;

public interface OrdersService {

	OrdersDetailsEntity addItems(OrdersDetailsEntity ordersDetailsEntity) throws ProductTransactionException;
	ResponseDataModel confirmOrders(OrdersEntity ordersEntity);
}
	