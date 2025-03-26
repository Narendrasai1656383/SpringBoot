package com.spring.registerAndLogin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.registerAndLogin.dto.UserRequest;
import com.spring.registerAndLogin.entity.User;
import com.spring.registerAndLogin.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	public String registerUser(UserRequest userRequest) {
		if(userRepo.findByUserName(userRequest.getUserName()).isPresent()) {
			return "User Already Exists";
		}
		if(userRepo.findByEmail(userRequest.getEmail()).isPresent()) {
			return "Use alternative Email/Email is already used";
		}
		User user=new User(
				0,
				userRequest.getUserName(),
				userRequest.getEmail(),
				userRequest.getPassword()
				);
		userRepo.save(user);
		return "User Registered Successfully";
	}
	public Optional<User> loginUser(String userName, String password) {
		Optional<User> user=userRepo.findByUserName(userName);
		if(user.isPresent() && password.equals(user.get().getPassword())) {
			return user;
		}
		return Optional.empty();
	}
}
