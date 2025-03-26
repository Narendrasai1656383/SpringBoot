package com.spring.registerAndLogin.service;

import java.util.Optional;

import com.spring.registerAndLogin.dto.UserRequest;
import com.spring.registerAndLogin.entity.User;

public interface UserServiceInterface {
	public String registerUser(UserRequest userRequest);
	public Optional<User> loginUser(String userName, String password);
}
