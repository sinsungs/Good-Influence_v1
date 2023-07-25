package com.youtubers.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtubers.Service.UserSerivce;
import com.youtubers.entity.KakaoProfile;
import com.youtubers.entity.OAuthToken;
import com.youtubers.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private UserSerivce userService;

	
	@PostMapping("/user/login")
	public ResponseEntity<String> login() {
//		return ResponseEntity.ok("로그인 성공");
		return ResponseEntity.ok().body(userService.login("", ""));
	}
	
	@PostMapping("/user/join")
	public ResponseEntity<User> joinUser(@RequestBody User user) {
		System.out.println(user);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		User joinUser = userService.joinUser(user);
		return ResponseEntity.ok(joinUser);
	}
	
//	@PostMapping("/register")
//    public ResponseEntity<Youtuber> createYoutuber(@RequestBody YoutuberDTO dto) {
//        Youtuber createdYoutuber = youtuberService.createYoutuber(dto);
//        return ResponseEntity.ok(createdYoutuber);
//    }
	
	

	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) {
		
		// POST 방식으로 key=value 데이터 요청 
		RestTemplate rt = new RestTemplate();
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "b58919f7c93ec635d5c0b3697d4aac6b");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
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
		
		System.out.println(kakaoProfile);
		
		userService.KakaoTest(kakaoProfile);
		
		// User 오브젝트 : username , password , email
//		System.out.println("카카오 아이디(번호):"+kakaoProfile.getId());
//		System.out.println("카카오 이메일:"+kakaoProfile.getKakao_account().getEmail());
//		
//		System.out.println("회원가입 유저네임:" + kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
//		System.out.println("회원가입 이메일:"+kakaoProfile.getKakao_account().getEmail());
//		
//		UUID garbagePassword = UUID.randomUUID();
//		System.out.println("회원가입 패스워드:"+garbagePassword);
		
//		User kakaoUser = User.builder()
//			.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
//			.password(garbagePassword.toString())
//			.email(kakaoProfile.getKakao_account().getEmail())
//			.build();
//		
//		// 가입자 혹은 비가입자 체크해서 처리 
//		User originUser = userService.회원찾기(kakaoUser.getUsername());
//		
//		if(originUser == null) {
//			userService.회원가입(kakaoUser);
//		}
//		
//		userService.회원가입(kakaoUser);
		
		return response2.getBody();
	}
}
