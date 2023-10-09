package com.youtubers.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtubers.entity.RoleType;
import com.youtubers.entity.User;
import com.youtubers.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/dummy/join")
	public String join(User user) {
		
		System.out.println(user.getUsername());
		
		user.setRole(RoleType.USER);
		
		System.out.println(user.getRole());
		userRepository.save(user);
		
		return"회원가입이 완료되었습니다";
	}
}
