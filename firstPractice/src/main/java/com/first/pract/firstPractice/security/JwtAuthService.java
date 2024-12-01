package com.first.pract.firstPractice.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtAuthService {

	private static final String SECRET = "aaaaaaaaaaaasssssssssssssssddddddddddddffffffffff";

	public String generateToken(String email) {
		Map<String, Object> claims=new HashMap<>();
		return createToken(claims,email);
	}

	private String createToken(Map<String, Object> claims, String email) {
		return Jwts.builder()
		.setClaims(claims)
		.setSubject(email)
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()+7200000))
		.signWith(getKey(),SignatureAlgorithm.HS256)
		.compact();
	}

	private Key getKey() {
		byte[] key=Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(key);
	}

	public String extractUsername(String token) {
		
		return extractClaims(token,Claims::getSubject);
	}

	private <T>T extractClaims(String token, Function<Claims, T> object) {
		// TODO Auto-generated method stub
		final Claims claim=extractAllClaims(token);
		return object.apply(claim);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return username.equals(userDetails.getUsername())&& !tokenExpired(token);
	}

	private boolean tokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token,Claims::getExpiration).before(new Date());
	}

	
}
