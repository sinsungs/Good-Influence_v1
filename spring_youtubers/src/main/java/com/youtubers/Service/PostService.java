package com.youtubers.Service;

import org.springframework.stereotype.Service;

import com.youtubers.dto.PostRequestDTO;
import com.youtubers.entity.Post;
import com.youtubers.entity.User;
import com.youtubers.repository.PostRepository;
import com.youtubers.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostService {
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;

    

    	public Long createPost(PostRequestDTO dto, String uploadedFileName) {
    	
    	User user = userRepository.findByEmail(dto.getWriter()).orElse(null);
    	
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setImageUrl(uploadedFileName);
        post.setUser(user);
        
        postRepository.save(post);
        
        Long postId = post.getPno();
        
        return postId;
//        return postYoutuber;
    }
    
    
}
