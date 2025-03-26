package com.spring.registerAndLogin.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.registerAndLogin.dto.OrderItemRequest;
import com.spring.registerAndLogin.dto.OrderRequest;
import com.spring.registerAndLogin.entity.Order;
import com.spring.registerAndLogin.entity.OrderItem;
import com.spring.registerAndLogin.entity.Product;
import com.spring.registerAndLogin.entity.User;
import com.spring.registerAndLogin.exception.ProductNotFoundException;
import com.spring.registerAndLogin.repository.OrderRepository;
import com.spring.registerAndLogin.repository.ProductRepository;
import com.spring.registerAndLogin.service.OrderServiceInterface;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class OrderService implements OrderServiceInterface{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private HttpSession httpSession;
	@Override
	@Transactional
	public Order placeOrder(OrderRequest orderRequest) throws ProductNotFoundException {
		System.out.println(orderRequest.toString());
		Order order=new Order();
		order.setUser((User)httpSession.getAttribute("userLoggedIn"));
		Set<OrderItem> orderItems=new HashSet<>();
		for(OrderItemRequest itemRequest:orderRequest.getOrderItems()) {
			Product product=productRepository.findById(itemRequest.getProductId())
					.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder(order);
			orderItem.setPrice(product.getPrice());
			orderItem.setProduct(product);
			orderItem.setQuantity(itemRequest.getQuantity());
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		return orderRepository.save(order);
	}
	
}
