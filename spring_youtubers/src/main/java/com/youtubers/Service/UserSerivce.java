package com.youtubers.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
public class UserSerivce {

	private final UserRepository userRepository;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	private Long expiredMs = 1000 * 60 * 60l;
	
	public String login(String userName, String password) {
		
		return JwtUtil.createJwt(userName, secretKey, expiredMs);
	}
	
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

	public User joinUser(User user) {
		// TODO Auto-generated method stub
		user.setRole(RoleType.USER);
        return userRepository.save(user);
	}
	
//    public Long createPost(PostRequestDTO dto) {
//    	
//        Post post = new Post();
//        post.setTitle(dto.getTitle());
//        post.setContent(dto.getContent());
//        postRepository.save(post);
//        
//        Long postId = post.getPno();
//        
//        return postId;
////        return postYoutuber;
//    }
	
	

}
