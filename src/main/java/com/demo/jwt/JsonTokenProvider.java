package com.demo.jwt;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.demo.secutiry.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JsonTokenProvider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final long JSON_TOKEN_VALIDITY = 604800000L;

	private static final Logger log = LoggerFactory.getLogger(JsonTokenProvider.class);

	@Value("jwt.secret")
	private String secret;

	public String generateToken(CustomUserDetails customerUserDetails) {
		Date date = new Date();
		Date expiryDate = new Date(date.getTime() + JSON_TOKEN_VALIDITY);
		return Jwts.builder().setSubject(customerUserDetails.getUsername()).setIssuedAt(date).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public String getUserNameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException e) {
			log.error("JWT token has expired");
		} catch (IllegalArgumentException e) {
			log.error("Unable to get JWT Token");
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT Token");
		}
		return false;
	}
}
