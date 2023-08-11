package com.youtubers.utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	public static String getUserName(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
				.getBody().get("userName", String.class);	
	}

	public static String createJwt(String userName, String secretKey, Long expiredMs) {
		Claims claims = Jwts.claims();
		claims.put("userName", userName);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiredMs))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
	public static boolean isExpired(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
				.getBody().getExpiration().before(new Date());
	}
	

}
