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
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JsonTokenProvider implements Serializable {

	@Value("${jwt.secret}")
	private String secret;

	private static final Logger log = LoggerFactory.getLogger(JsonTokenProvider.class);

	private static final Long JWT_TOKEN_VALIDITY = 604800000L;

	/**
	 * 
	 * @param customUserDetails
	 * create jwt from user
	 * @return
	 */
	public String generateToken(CustomUserDetails customUserDetails) {
		Date dateNow = new Date();
		Date expiryDate = new Date(dateNow.getTime() + JWT_TOKEN_VALIDITY);
		return Jwts.builder().setSubject(customUserDetails.getUsername()).setIssuedAt(dateNow).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	public String getUserNameFromToken(String token) {
		Claims clams = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return clams.getSubject();
	}

	/**
	 * 
	 * @param authToken
	 * @return
	 */

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
