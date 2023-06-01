package com.kong.king.spring.youtuber.service;

import org.springframework.stereotype.Service;

import com.kong.king.spring.youtuber.dto.PostDTO;
import com.kong.king.spring.youtuber.entity.Post;
import com.kong.king.spring.youtuber.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl implements PostService {
	
	private final PostRepository postRepository;
	
	// 게시물 등록 
	@Override
	public void createPost(PostDTO dto) {
		log.info("---BoardServiceImpl register()---" + dto);
		Post post = dtoToEntity(dto);
		postRepository.save(post); 
		
	}

}
