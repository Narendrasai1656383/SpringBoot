package com.spring.registerAndLogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin(origins = "http://192.168.33.89:8080", allowCredentials = "true")
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderServiceInterface orderService;
	@PostMapping("/placeOrder")
	@RequiredLogin
	public Order placeOrder(@RequestBody OrderRequest orderRequest) throws ProductNotFoundException {
		return orderService.placeOrder(orderRequest);
	}
	@GetMapping("/getAllOrders")
	@RequiredLogin
	public List<Order> getAllOrders(){
		return orderService.getAllProducts();
	}
}
