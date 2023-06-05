package com.kong.king.spring.youtuber.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kong.king.spring.youtuber.dto.PostDTO;
import com.kong.king.spring.youtuber.dto.PostRequestDTO;
import com.kong.king.spring.youtuber.entity.Post;
import com.kong.king.spring.youtuber.entity.PostYoutuber;
import com.kong.king.spring.youtuber.entity.Youtuber;
import com.kong.king.spring.youtuber.repository.PostRepository;
import com.kong.king.spring.youtuber.repository.YoutuberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl implements PostService {
	
	private final PostRepository postRepository;
	private final YoutuberRepository youtuberRepository;
	
	// 게시물 등록 
//	@Override
//	public Post createPost(PostDTO dto) {
//		log.info("---BoardServiceImpl register()---" + dto);
//		Post post = dtoToEntity(dto);
//		return postRepository.save(post); 
//	}
	
    @Override
    public Post createPost(PostRequestDTO dto) {
        Post post = dtoToEntity(dto);
        return postRepository.save(post);
    }

//	@Override
//	public boolean addYouTubersToPost(Long postId, List<Long> youtuberIds) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
//	public boolean addYouTubersToPost(Long postId, List<Long> youtuberIds) {
//	    Optional<Post> optionalPost = postRepository.findById(postId);
//	    if (optionalPost.isPresent()) {
//	        Post post = optionalPost.get();
//	        
//	        List<Youtuber> youtubers = new ArrayList<>();
//	        
//	        for (Long youtuberId : youtuberIds) {
//	            Optional<Youtuber> optionalYouTuber = youtuberRepository.findById(youtuberId);
//	            if (optionalYouTuber.isPresent()) {
//	            	
//	                Youtuber youtuber = optionalYouTuber.get();
//	                youtubers.add(youtuber);
//	                
//	            } else {
//	                // 해당 YouTuber ID에 해당하는 YouTuber가 없는 경우 처리 로직 작성
//	            }
//	        }
//	        
//	        post.getPostYoutubers().add((PostYoutuber) youtubers);
//	        postRepository.save(post);
//	        
//	        return true;
//	    } else {
//	        return false;
//	    }
//	}

}
