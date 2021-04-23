package com.demo.service;

import com.demo.entity.OrdersDetailsEntity;
import com.demo.entity.OrdersEntity;
import com.demo.exception.RecordNotFoundException;
import com.demo.model.ResponseDataModel;

public interface OrdersService {

	OrdersDetailsEntity addItems(OrdersDetailsEntity ordersDetailsEntity) throws RecordNotFoundException;
	ResponseDataModel confirmOrders(OrdersEntity ordersEntity);
}
	