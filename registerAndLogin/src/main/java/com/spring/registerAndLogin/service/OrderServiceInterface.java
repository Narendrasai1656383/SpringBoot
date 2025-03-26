package com.spring.registerAndLogin.service;

import com.spring.registerAndLogin.dto.OrderRequest;
import com.spring.registerAndLogin.entity.Order;
import com.spring.registerAndLogin.exception.ProductNotFoundException;

public interface OrderServiceInterface {
	public Order placeOrder(OrderRequest orderRequest) throws ProductNotFoundException;
}
