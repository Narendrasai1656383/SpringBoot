package com.spring.registerAndLogin.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.registerAndLogin.exception.NoLoggedInUserException;
import com.spring.registerAndLogin.entity.User;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class LoginAspect {
	@Autowired
	private HttpSession httpSession;
	@Pointcut("@annotation(com.spring.registerAndLogin.aspect.RequiredLogin)")
	public void requiredLoginPointCut() {
		
	}
	@Before("requiredLoginPointCut()")
	public void checkLogin() throws NoLoggedInUserException {
		if(httpSession.getAttribute("userLoggedIn")==null) {
			throw new NoLoggedInUserException("No user is logged in!");
		}
		String loggedInUser=((User)httpSession.getAttribute("userLoggedIn")).getUserName();
		if(loggedInUser==null) {
			throw new NoLoggedInUserException("No user is logged in!");
		}
	}
}
