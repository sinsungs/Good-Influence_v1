package com.youtubers.controller;


import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtubers.Service.UserService;
import com.youtubers.dto.LoginRequest;
import com.youtubers.dto.UserDTO;
import com.youtubers.entity.KakaoProfile;
import com.youtubers.entity.OAuthToken;
import com.youtubers.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	// 회원 가입
	@PostMapping("/user/join")
	public ResponseEntity<String> joinUser(@RequestBody UserDTO dto) {
		
		String result = userService.Join(dto);
		
		return ResponseEntity.ok(result);
	}
	
	
	// JWT 로그인 
	@PostMapping("/user/jwtlogin")
	public ResponseEntity<String> jwtLogin(@RequestBody LoginRequest dto) {
//		return ResponseEntity.ok("로그인 성공");
		return ResponseEntity.ok().body(userService.jwtLogin(dto.getEmail(), dto.getPassword()));
	}
	
	
	// 일반 로그인 
	@PostMapping("/user/login")
	public ResponseEntity<String> Login(@RequestBody User user, HttpSession session) {
//		return ResponseEntity.ok("로그인 성공");
		
		String principal = userService.Login(user);
		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
		
		return ResponseEntity.ok(principal);
	}
	
	// 프로필 정보 
	@PostMapping("/user/profile")
	public ResponseEntity<?> Profile(Authentication authentication){
		
    	System.out.println("정보" + authentication.getName());
    	
//    	dto.setWriter(authentication.getName());
    	
    	User user = userService.회원찾기(authentication.getName());
        
        return ResponseEntity.ok(user);
	}
	
	// 마이페이지 
	@PostMapping("/user/mypage")
	public ResponseEntity<?> Mypage(Authentication authentication){
		
    	System.out.println("정보" + authentication.getName());
    	
    	User user = userService.회원찾기(authentication.getName());
        
        return ResponseEntity.ok(user);
	}
	
	
	
	// 카카오 로그인 
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {
		
		log.info(code);
		
		// 토큰을 받아오기위해 POST 요청 
		// POST 방식으로 key=value 데이터 요청 
		RestTemplate rt = new RestTemplate();
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "b58919f7c93ec635d5c0b3697d4aac6b");
		params.add("redirect_uri", "https://goodinfluence.shop/login/oauth2/callback/kakao");
		params.add("code", code);
		
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers);
		
		// Http 요청하기 - post방식으로 - 그리고 response 응답받기
		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);
		
		// 카카오 Response 값 매핑 ( json ) 
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("카카오 엑세스 토큰" + oauthToken.getAccess_token());
		
		
		
		// 토큰을 이용한 POST 요청 사용자 정보 조회 
		// POST 방식으로 key=value 데이터 요청 
		RestTemplate rt2 = new RestTemplate();
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		
		// Http 요청하기 - post방식으로 - 그리고 response 응답받기
		ResponseEntity<String> response2 = rt2.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.POST,
			kakaoProfileRequest2,
			String.class
		);
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(kakaoProfile);
//		System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        log.info("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        log.info("카카오 유저네임 : " + kakaoProfile.getKakao_account().getEmail()+"_"+ kakaoProfile.getId());
		
        // 가입자 혹은 비가입자 체크해서 처리 
        User originUser = userService.회원찾기(kakaoProfile.getKakao_account().getEmail());
        log.info(originUser);
        
        if(originUser.getEmail()==null) {
            // 회원가입 처리
    		User user = userService.KakaoTest(kakaoProfile);
    		
            String jwt = userService.jwtLogin(user.getEmail(), "examplePassword");
            
    		return jwt ;
        }

        String jwt = userService.jwtLogin(originUser.getEmail(), "examplePassword");
        
		return jwt ;
	}
}
