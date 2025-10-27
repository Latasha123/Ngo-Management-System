package com.example.ngo.util;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${secter_key}")
	private String secter_key;

	private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secter_key.getBytes());
	}

	public String generateToken(String email, String role) {
		return Jwts.builder().setSubject(email).addClaims(Map.of("role", role)).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	public String extractEmail(String token) {
		return extractAllClaims(token).getSubject();
	}

	public String extractRole(String token) {
		return extractAllClaims(token).get("role", String.class);
	}

	public boolean isTokenValid(String token) {
		return extractAllClaims(token).getExpiration().after(new Date());
	}

}
