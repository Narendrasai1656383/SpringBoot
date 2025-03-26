package com.spring.registerAndLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.registerAndLogin.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}