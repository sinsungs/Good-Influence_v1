package com.youtubers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.youtubers.Service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
// 모든 인증에 시큐리티 적용이 기본값으로 적용된다 
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final UserService userSerivce;
	
	@Value("${jwt.secret}")
	private String secretKey; 
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.httpBasic().disable()  			// HTTP Basic 인증 비활성화
				.csrf().disable() 					// CSRF 보안 기능 비활성화
				.cors()								// CORS 활성화
				.and() 								// and란 설정 연결 

				.authorizeRequests()				// 요청에 대한 권한 설정 시작
//				.antMatchers("/**").permitAll()   	// 모든 경로에 대한 접근 허용
				.antMatchers("/user/join","/user/login","/user/jwtlogin").permitAll()   // 회원가입 , 로그인 전체 허용
//				.antMatchers(HttpMethod.POST, "/**").authenticated()  
				// authenticated란 인증된 사용자에게만 접근을 허용 , antMatchers랑 같이사용 
				
//			    .antMatchers("/admin/**").hasRole("ADMIN") // ADMIN 권한만 접근 가능
//			    .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // USER 또는 ADMIN 권한 접근 가능


				.and()
				
				.sessionManagement()               // 세션 관리 설정 시작
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)  
				// JWT를 사용하기에 세션을 생성하지 않고 상태를 관리하지 않도록 설정
				.and()
				
				.addFilterBefore(new JwtFilter(userSerivce, secretKey), UsernamePasswordAuthenticationFilter.class)
				// JwtFilter 추가 => jwt토큰 받고 해석 
				.build();
				// SecurityFilterChain 빌드하여 반환
	}
}
