package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.OrdersDetailsEntity;
import com.demo.exception.ProductTransactionException;
import com.demo.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	
	@PostMapping("/api/add")
	public OrdersDetailsEntity addItems(OrdersDetailsEntity ordersEntity) throws ProductTransactionException {
		
		return ordersService.addItems(ordersEntity);
	}
}
