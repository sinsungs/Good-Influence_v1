package com.youtubers.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youtubers.dto.ProfileDTO;
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
	
	
	// jwt 로그인 
	// 로컬 로그인 예외 똑같이 들어가야함
	@Value("${jwt.secret}")
	private String secretKey;
	// 시크릿키를 이렇게 등록하지 않으면 git에서 전세계 사람들이 다 볼 수 있음
	
	private Long expiredMs = 1000 * 60 * 60l;
	// 1시간으로 만들어주기 , 밀리세컨드 , long타입이라 l 붙이기
	
	@Transactional(readOnly = true)
	public String jwtLogin(String email, String password) {
		
		// email 없음
		User selectedUser = userRepository.findByEmail(email).orElse(null);
//		.orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOTFOUND, "존재하지 않는 아이디 입니다."));
	
			if(selectedUser == null) {
				return "존재하지 않는 아이디 입니다.";
			}
//				
						
		// password 틀림 
		if (!encoder.matches( password, selectedUser.getPassword())) {
//			throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 했습니다.");
			return "패스워드를 잘못 입력 했습니다.";
		} 
		
		return JwtUtil.createJwt(email, secretKey, expiredMs);
	}
	
	// 로컬 로그인 
	@Transactional(readOnly = true) 
	public String Login(User user) {
		
		// email 없음
		User selectedUser = userRepository.findByEmail(user.getEmail())
				.orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOTFOUND, "존재하지 않는 아이디 입니다."));
				
		// password 틀림 
		if (!encoder.matches( user.getPassword(), selectedUser.getPassword())) {
			throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 했습니다.");
		} 
		
		return "성공";
//		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	
	}
	
	
	
	// 로컬 회원가입 
	@Transactional
	public String Join(UserDTO dto) {

		// email 공백 check 
		if(dto.getEmail() == "") {
			return "이메일을 입력해 주세요.";
		}
		
		// password 공백 check
		if(dto.getPassword() == "") {
			return "비밀번호를 입력해 주세요.";
		} else if (dto.getPassword().length() < 6) {
		    return "비밀번혼는 6자리 이상이어야 합니다.";
		}
		
		// username 공백 및 글자 수 check
		if (dto.getUsername() == "") {
		    return "닉네임을 입력해 주세요.";
		} else if (dto.getUsername().length() == 1) {
		    return "닉네임은 최소 2글자 이상이어야 합니다.";
		}
		
		
		
		// email 중복 check 
		User userID = userRepository.findByEmail(dto.getEmail()).orElse(null);
		
		if(userID != null) {
			return "이미 사용중인 아이디입니다.";
		}
			
//				.ifPresent((user) -> {
//					
//					throw new AppException(ErrorCode.USERNAME_NOTFOUND, "이미 사용중인 아이디입니다.");
//				
//				});
					
		
		// username 중복 check
				User userName = userRepository.findByUsername(dto.getUsername()).orElse(null);
				
				if(userName != null) {
					return "닉네임 '" + userName.getUsername() + "' 사용중입니다.";
				}
				
//				.ifPresent((user) -> {
//					
//					throw new AppException(ErrorCode.USERNAME_DUPLICATED , user.getUsername() + "는 이미 있습니다.");
//		
//				});
		
		// 저장
		User user = User.builder()
				.username(dto.getUsername())
				.password(encoder.encode(dto.getPassword()))
				.email(dto.getEmail())
				.role(RoleType.USER)
				.oauth("local")
				.amount(100000)
				.experience(0)
				.sns("유저")
				.imageUrl("https://sinsung-s3.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%86%A1%EA%B8%B0%EB%B3%B8%ED%94%84%EB%A1%9C%ED%95%84.jpeg")
				.build();
		
        userRepository.save(user);
        
        return "회원가입에 성공하셨습니다.";
        
	}

	
	// 카카오 로그인
	@Transactional
	public User KakaoTest(KakaoProfile kakao) {
		
		User user = User.builder()
				.username(kakao.getKakao_account().getEmail()+"_"+ kakao.getId())
				.email(kakao.getKakao_account().getEmail())
				.password(encoder.encode("examplePassword"))
				.role(RoleType.USER)
				.oauth("kakao")
				.amount(100000)
				.experience(0)
				.sns("default")
				.imageUrl("https://sinsung-s3.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%86%A1%EA%B8%B0%EB%B3%B8%ED%94%84%EB%A1%9C%ED%95%84.jpeg")
				.build();
		
		return userRepository.save(user);
		
	}
	
	
	// 이미 가입한 유저인지 체크하고 처리하는 로직 추가 해야함 
	@Transactional
	public User 회원찾기(String email) {
		
		User user = userRepository.findByEmail(email).orElseGet(()->{
			return new User();
		});
		
		return user;
		
	}

	






	

}
