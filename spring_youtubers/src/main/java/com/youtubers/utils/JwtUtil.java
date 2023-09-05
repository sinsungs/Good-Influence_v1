package com.youtubers.utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	// JWT 기능 모음 
	
	// UserName 꺼내기 
	public static String getUserName(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
				.getBody().get("email", String.class);	
	}

	
	// JWT 토큰 생성 
	public static String createJwt(String email, String secretKey, Long expiredMs) {
		Claims claims = Jwts.claims();
		claims.put("email", email);
		// 토큰에 UserName 담기
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiredMs))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
	
	// JWT 시간 초과 검증 
	public static boolean isExpired(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
				.getBody().getExpiration().before(new Date());
	}
	// expiration 의 전인지 확인 

}
