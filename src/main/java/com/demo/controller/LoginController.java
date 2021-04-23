package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.UsersEntity;
import com.demo.jwt.JsonTokenProvider;
import com.demo.model.LoginResponse;
import com.demo.model.ResponseDataModel;
import com.demo.secutiry.CustomUserDetails;
import com.demo.service.UserService;

@RestController  
@RequestMapping(value = "/api")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JsonTokenProvider jsonTokenProvider;

	@Autowired
	UserService userService;

	/**
	 * 
	 * @param usersEntity
	 * @return
	 */
	@PostMapping("/login")
	public LoginResponse login(@RequestBody UsersEntity usersEntity) {
		String jwt = jsonTokenProvider.generateToken((CustomUserDetails) authenticationManager
				.authenticate(
						new UsernamePasswordAuthenticationToken(usersEntity.getUserName(), usersEntity.getPassword()))
				.getPrincipal());
		return new LoginResponse(jwt);

	}

	/**
	 * 
	 * @param usersEntity
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/add")
	public ResponseDataModel addUser(@Valid @RequestBody UsersEntity usersEntity) throws Exception {
		return userService.addUser(usersEntity);

	}

}
