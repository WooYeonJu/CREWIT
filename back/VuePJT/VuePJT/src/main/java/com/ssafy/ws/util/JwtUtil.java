package com.ssafy.ws.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String SALT = "SSAFY_NonMajor_JavaTrack_SecretKey";
	private SecretKey secretKey = Keys.hmacShaKeyFor(SALT.getBytes(StandardCharsets.UTF_8));

	public String createToken(String email) {
		Date exp = new Date(System.currentTimeMillis() + 1000*60*60); //  유효기간 1시간
		
		return Jwts.builder().header().add("typ", "JWT").and()
				.claim("email", email).expiration(exp)
				.signWith(secretKey).compact();
	}


	public Jws<Claims> validate(String token) {
		 return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
	}
}
