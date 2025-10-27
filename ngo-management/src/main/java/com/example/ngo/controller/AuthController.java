package com.example.ngo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.dtos.UserDto;
import com.example.ngo.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseDto register(@RequestBody UserDto userDto) {
		return userService.registerUser(userDto);
	}

	@PostMapping("login")
	public ResponseDto login(@RequestBody UserDto userDto) {
		return userService.doLogin(userDto);
	}
}