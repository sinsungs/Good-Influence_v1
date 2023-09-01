package com.youtubers.Service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youtubers.dto.UserDTO;
import com.youtubers.entity.KakaoProfile;
import com.youtubers.entity.RoleType;
import com.youtubers.entity.User;
import com.youtubers.exception.AppException;
import com.youtubers.exception.ErrorCode;
import com.youtubers.repository.UserRepository;
import com.youtubers.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	
	// 로컬 회원가입 
	@Transactional
	public boolean Join(UserDTO dto) {
		
		// email 중복 check 
//		User selectedUser = 
				userRepository.findByEmail(dto.getEmail())
				.ifPresent((user) -> {
					
					throw new AppException(ErrorCode.USERNAME_NOTFOUND, "이미 사용중인 아이디입니다.");
				
				});
					
		
		// username 중복 check
		userRepository.findByUsername(dto.getUsername())
				.ifPresent((user) -> {
					
					throw new AppException(ErrorCode.USERNAME_DUPLICATED , user.getUsername() + "는 이미 있습니다.");
		
				});
		
		// 저장
		User user = User.builder()
				.username(dto.getUsername())
				.password(encoder.encode(dto.getPassword()))
				.email(dto.getEmail())
				.role(RoleType.USER)
				.build();
		
        userRepository.save(user);
        
        return true;
        
	}
	
	// 로컬 로그인 
	@Transactional(readOnly = true) // select 할 때 트랜잭션 시작 , 서비스 종료시에 트랜잭션 종료 ( 정합성 ) 
	public String Login(User user) {
		
		// email 없음
		User selectedUser = userRepository.findByEmail(user.getEmail())
				.orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOTFOUND, "존재하지 않는 아이디 입니다."));
				
		// password 틀림 
		if (!encoder.matches(selectedUser.getPassword(), user.getPassword())) {
			throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 했습니다.");
		} 
		
		return "성공";
//		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	
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
