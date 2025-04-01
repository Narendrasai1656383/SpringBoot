package com.spring.registerAndLogin.entity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime orderDate=LocalDateTime.now();
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id", nullable=false)
	@JsonBackReference
	private User user;
	@OneToMany(mappedBy ="order", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	private Set<OrderItem> orderItems=new CopyOnWriteArraySet<>();
	public Set<OrderItem> getOrderItems() {
	    return Collections.unmodifiableSet(orderItems);
	}
	private Double orderPrice;
}