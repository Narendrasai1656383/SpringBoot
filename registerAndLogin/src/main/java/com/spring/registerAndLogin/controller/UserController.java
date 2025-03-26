package com.spring.registerAndLogin.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.registerAndLogin.aspect.RequiredLogin;
import com.spring.registerAndLogin.dto.LoginRequest;
import com.spring.registerAndLogin.dto.UserRequest;
import com.spring.registerAndLogin.entity.User;
import com.spring.registerAndLogin.service.UserServiceInterface;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private UserServiceInterface userService;
	@PostMapping("/register")
	public String registerUser(@Valid @RequestBody UserRequest userRequest) {
		return userService.registerUser(userRequest);
	}
	@PostMapping("/login")
	public String loginUser(@Valid @RequestBody LoginRequest loginRequest) {
		 Optional<User> user=userService.loginUser(loginRequest.getUserName(),loginRequest.getPassword());
		if(user.isPresent()) {
			httpSession.setAttribute("userLoggedIn", user.get());
			return "Login successful! Welcome "+user.get().getUserName();
		}
		return "Invalid User Name or Password";
	}
	@GetMapping("/details")
	@RequiredLogin
	public User getUserDetails() {
			return (User)httpSession.getAttribute("userLoggedIn"); 
	}
	@GetMapping("/logout")
	@RequiredLogin
	public String logoutUser(HttpSession httpSession) {
		httpSession.invalidate();
		return "Logged Out Successfully";
	}
}
