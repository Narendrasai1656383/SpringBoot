package com.spring.registerAndLogin.service;

import java.util.List;

import com.spring.registerAndLogin.dto.ProductRequest;
import com.spring.registerAndLogin.entity.Product;

public interface ProductServiceInterface {
	public Product addProduct(ProductRequest productRequest);
	public List<Product> getProducts();
}