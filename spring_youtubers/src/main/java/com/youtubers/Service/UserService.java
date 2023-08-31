package com.youtubers.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youtubers.dto.UserDTO;
import com.youtubers.entity.KakaoProfile;
import com.youtubers.entity.RoleType;
import com.youtubers.entity.User;
import com.youtubers.repository.UserRepository;
import com.youtubers.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

	private final UserRepository userRepository;
	
	// 로컬 회원가입 
	@Transactional
	public boolean Join(UserDTO dto) {
		
		// username 중복 check
		userRepository.findByUsername(dto.getUsername())
				.ifPresent(user -> {
					
					throw new RuntimeException(user.getUsername() + "는 이미 있습니다.");
		
				});
		
		// 저장
		User user = User.builder()
				.username(dto.getUsername())
				.password(dto.getPassword())
				.email(dto.getEmail())
				.role(RoleType.USER)
				.build();
		
        userRepository.save(user);
        
        return true;
        
	}
	
	// 로컬 로그인 
	@Transactional(readOnly = true) // select 할 때 트랜잭션 시작 , 서비스 종료시에 트랜잭션 종료 ( 정합성 ) 
	public User Login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	}
	
	// jwt 로그인 
	@Value("${jwt.secret}")
	private String secretKey;
	
	private Long expiredMs = 1000 * 60 * 60l;
	
	public String jwtLogin(String userName, String password) {
		
		return JwtUtil.createJwt(userName, secretKey, expiredMs);
	}
	
	// 카카오 로그인
	public void KakaoTest(KakaoProfile kakao) {
		
		log.info("---test---" + kakao);
		
		User user = new User();
		user.setUsername("exampleUser");
		user.setEmail(kakao.getKakao_account().getEmail());
		user.setPassword("examplePassword");
		user.setRole(RoleType.USER);
		userRepository.save(user); 
		
//		Youtuber youtuber = dtoToEntity(dto);
		return;
		
	}
	

}
