package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.UsersEntity;
import com.demo.jwt.JsonTokenProvider;
import com.demo.model.LoginResponse;
import com.demo.secutiry.CustomUserDetails;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JsonTokenProvider jsonTokenProvider;

	@PostMapping("/login")
	public LoginResponse login(UsersEntity usersEntity) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usersEntity.getUserName(), usersEntity.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		System.out.println();
		// Trả về jwt cho người dùng.
		String jwt = jsonTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		return new LoginResponse(jwt);
	}
}
