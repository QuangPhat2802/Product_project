package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.OrdersDetailsEntity;
import com.demo.entity.OrdersEntity;
import com.demo.exception.RecordNotFoundException;
import com.demo.model.ResponseDataModel;
import com.demo.service.OrdersService;

@RestController
@RequestMapping("/api")
public class OrdersController {

	@Autowired
	OrdersService ordersService;

	/**
	 * 
	 * @param ordersDetailsEntity
	 * @return
	 * @throws RecordNotFoundException
	 */
	@PostMapping("/orders/add")
	public OrdersDetailsEntity addItems(OrdersDetailsEntity ordersDetailsEntity) throws RecordNotFoundException {
		return ordersService.addItems(ordersDetailsEntity);
	}

	/**
	 * 
	 * @param ordersEntity
	 * @return
	 */
	@PostMapping("/orders/confirm")
	public ResponseDataModel confirmOrders(OrdersEntity ordersEntity) {

		return ordersService.confirmOrders(ordersEntity);
	}
}
