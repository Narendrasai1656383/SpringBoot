package com.spring.registerAndLogin.service;

import java.util.List;

import com.spring.registerAndLogin.dto.OrderRequest;
import com.spring.registerAndLogin.entity.Order;
import com.spring.registerAndLogin.exception.ProductNotFoundException;

public interface OrderServiceInterface {
	public Order placeOrder(OrderRequest orderRequest) throws ProductNotFoundException;
	public List<Order> getAllProducts();
}
