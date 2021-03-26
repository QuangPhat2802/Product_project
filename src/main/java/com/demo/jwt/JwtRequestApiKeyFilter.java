//package com.demo.jwt;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//
//public class JwtRequestApiKeyFilter implements Filter {
//	@Value("{myapp.http.auth-token-header-name}")
//	private String principalRequestHeader;
//
//	@Value("{myapp.http.auth.token}")
//	private String principalRequestValue;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		ApiKeyAuthFilter apiKeysFilter = new ApiKeyAuthFilter(principalRequestHeader);
//		apiKeysFilter.setAuthenticationManager(new AuthenticationManager() {
//
//			@Override
//			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//				String principal = (String) authentication.getPrincipal();
//				if (!principalRequestValue.equals(principal)) {
//					throw new BadCredentialsException("api not found");
//				}
//				authentication.setAuthenticated(true);
//				System.out.println(authentication);
//				return authentication;
//			}
//		});
//
//	}
//
//}
