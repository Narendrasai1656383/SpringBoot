package com.spring.registerAndLogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.registerAndLogin.dto.ProductRequest;
import com.spring.registerAndLogin.entity.Product;
import com.spring.registerAndLogin.service.ProductServiceInterface;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductServiceInterface productService;
	@PostMapping("/add")
	public Product addProduct(@RequestBody ProductRequest prodRequest) {
		return productService.addProduct(prodRequest);
	}
	@GetMapping("/get")
	public List<Product> getProducts(){
		return productService.getProducts();
	}
}
