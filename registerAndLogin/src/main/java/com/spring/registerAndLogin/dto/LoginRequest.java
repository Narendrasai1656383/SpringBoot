package com.spring.registerAndLogin.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	@NotNull(message="User Name is mandatory")
	private String userName;
	@NotNull
	@Pattern(regexp = "^.{8,}$", message = "Password must contain at least one letter, one digit and be at least 8 characters long.")
	private String password;
}
