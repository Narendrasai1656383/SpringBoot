package com.spring.registerAndLogin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.registerAndLogin.dto.ProductRequest;
import com.spring.registerAndLogin.entity.Product;
import com.spring.registerAndLogin.repository.ProductRepository;
import com.spring.registerAndLogin.service.ProductServiceInterface;

@Service
public class ProductService implements ProductServiceInterface {
	@Autowired
	private ProductRepository productRepository;
	@Override
	public Product addProduct(ProductRequest productRequest) {
		Product product=new Product(null,
				productRequest.getName(),
				productRequest.getDescription(),
				productRequest.getPrice()
				);
		return productRepository.save(product);
	}
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
}