package com.kong.king.spring.youtuber.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kong.king.spring.youtuber.dto.JoinRequest;
import com.kong.king.spring.youtuber.dto.LoginRequest;
import com.kong.king.spring.youtuber.entity.User;
import com.kong.king.spring.youtuber.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	//loginId 중복 체크
	public boolean checkLoginIdDuplicate(String loginId) {
		return userRepository.existsByLoginId(loginId);
	}
	
	// nickname 중복 체크
	public boolean checkNicknameDuplicate(String nickname) {
		return userRepository.existsByNickname(nickname);
	}
	
	// 회원가입 기능
   public void join(JoinRequest req) {
        userRepository.save(req.toEntity());
    }
	
	// 로그인 기능
   public User login(LoginRequest req) {
       Optional<User> optionalUser = userRepository.findByLoginId(req.getLoginId());

       // loginId와 일치하는 User가 없으면 null return
       if(optionalUser.isEmpty()) {
           return null;
       }
		
		User user = optionalUser.get();
		
		// 찾아온 User의 password와 입력된 password가 다르면 null return
		if(!user.getPassword().equals(req.getPassword())) {
			return null;
		}
		
		return user;
	}
	
}









