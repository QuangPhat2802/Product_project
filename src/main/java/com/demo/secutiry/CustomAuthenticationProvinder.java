//package com.demo.secutiry;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.demo.entity.UsersEntity;
//import com.demo.service.UserService;
//
//@Component
//public class CustomAuthenticationProvinder implements AuthenticationProvider {
//
//	@Autowired
//	UserService userService;
//	
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		String name = authentication.getName();
//		String password = authentication.getCredentials().toString();
//		String passwordEncode = new BCryptPasswordEncoder().encode(password);
//		
//		UsersEntity login = userService.login(name, passwordEncode);
//		
//		if(login.getUserName().equals(name) && login.getPassword().equals(passwordEncode)) {
//			
//		}
//		return null;
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
