package com.spring.registerAndLogin.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	private Set<OrderItemRequest> orderItems;
}
