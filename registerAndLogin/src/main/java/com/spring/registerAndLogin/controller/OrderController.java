package com.spring.registerAndLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.registerAndLogin.aspect.RequiredLogin;
import com.spring.registerAndLogin.dto.OrderRequest;
import com.spring.registerAndLogin.entity.Order;
import com.spring.registerAndLogin.exception.ProductNotFoundException;
import com.spring.registerAndLogin.service.OrderServiceInterface;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderServiceInterface orderService;
	@PostMapping
	@RequiredLogin
	public Order placeOrder(@RequestBody OrderRequest orderRequest) throws ProductNotFoundException {
		return orderService.placeOrder(orderRequest);
	}
}
