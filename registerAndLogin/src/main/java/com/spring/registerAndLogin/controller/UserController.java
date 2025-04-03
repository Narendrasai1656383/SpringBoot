package com.spring.registerAndLogin.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://192.168.33.89:8080", allowCredentials = "true")
public class UserController {
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private UserServiceInterface userService;
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequest userRequest) {
		return new ResponseEntity<String>(userService.registerUser(userRequest), HttpStatus.OK);
	}
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
		 Optional<User> user=userService.loginUser(loginRequest.getUserName(),loginRequest.getPassword());
		if(user.isPresent()) {
			httpSession.setAttribute("userLoggedIn", user.get());
			return new ResponseEntity<String>("Login successful! Welcome "+user.get().getUserName(),HttpStatus.OK);
		}
		return new ResponseEntity<String>("Invalid User Name or Password",HttpStatus.NOT_FOUND);
	}
	@GetMapping("/details")
	@RequiredLogin
	public User getUserDetails() {
			return (User)httpSession.getAttribute("userLoggedIn"); 
	}
	@GetMapping("/logout")
	@RequiredLogin
	public ResponseEntity<String> logoutUser(HttpSession httpSession) {
		httpSession.invalidate();
		return new ResponseEntity<String>("Logged Out Successfully",HttpStatus.OK);
	}
}
