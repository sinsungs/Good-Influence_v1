package com.youtubers.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.youtubers.Service.UserService;
import com.youtubers.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter{
	
	private final UserService userService;
	private final String secretKey;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Header에서 토큰 꺼내기 
		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("authentication : {}", authorization);
		
		
		// token안보내면 Block
		if(authorization == null || !authorization.startsWith("Bearer")) {
			log.error("authentication을 잘못 보냈습니다.");
			filterChain.doFilter(request, response);
			return;
		}
		
		// Token꺼내기 
		String token = authorization.split(" ")[1];
		
		// Token Expired 되었는지 여부 
		if (JwtUtil.isExpired(token, secretKey)) {
			log.error("Token이 만료 되었습니다");
			filterChain.doFilter(request, response);
			return;
		}
		
		// UserName Token에서 꺼내기 
		String userName = JwtUtil.getUserName(token, secretKey);
		log.info("userName:{}", userName);
		
		// 권한 부여
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER")));
		
		// Detail을 넣어줍니다.
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
		
	}

}
