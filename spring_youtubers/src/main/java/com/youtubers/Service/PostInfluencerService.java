package com.youtubers.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.youtubers.dto.PostRequestDTO;
import com.youtubers.dto.PostResponseDTO;
import com.youtubers.entity.Influencer;
import com.youtubers.entity.Post;
import com.youtubers.entity.PostInfluencer;
import com.youtubers.repository.InfluencerRepository;
import com.youtubers.repository.PostInfluencerRepository;
import com.youtubers.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostInfluencerService{

	private final PostRepository postRepository;
	private final InfluencerRepository influencerRepository;
	private final PostInfluencerRepository postInfluencerRepository;
	
	
      public PostResponseDTO createInfluencerPost(PostRequestDTO dto) {
    	  
    	  List<PostInfluencer> postInfluencers = dtoToEntity(dto);
        
    	  postInfluencerRepository.saveAll(postInfluencers);
        
        return entityToDTO(postInfluencers.get(0));
        
    }
      
    
    public List<PostResponseDTO> getList() {
    	
        List<PostInfluencer> postInfluencers = postInfluencerRepository.findAll();
        
        List<PostResponseDTO> dtoList = new ArrayList<>();
        
        for (PostInfluencer postInfluencer : postInfluencers) {
            dtoList.add(entityToDTO(postInfluencer));
        }
        
        return dtoList;
    }
    
    
//    
//    	PostInfluencer dtoToEntity(PostRequestDTO dto) {
//    		
//	        Post post = postRepository.findById(dto.getPno()).orElseThrow(() -> new RuntimeException("Post not found"));
//	        Influencer influencer = influencerRepository.findById(dto.getIno()).orElseThrow(() -> new RuntimeException("Influencer not found"));
//		    
//	        
//	        PostInfluencer postInfluencer = PostInfluencer.builder()
//	                .post(post)
//	                .influencer(influencer)
//	                .build();
//	        
//	        return postInfluencer;
//    }
    
    
		private List<PostInfluencer> dtoToEntity(PostRequestDTO dto) {
		
	    	Post post = postRepository.findById(dto.getPno()).orElseThrow(() -> new RuntimeException("Post not found"));
	        
	    	List<Long> influencerIds = Arrays.asList(dto.getIno(), dto.getSecondino(), dto.getThirdino());
	    	
	        List<PostInfluencer> postInfluencers = influencerIds.stream()
	                .map(influencerId -> createPostInfluencer(post, influencerId))
	                .collect(Collectors.toList());
	        
	    return postInfluencers;
        
		}
    		
		private PostInfluencer createPostInfluencer(Post post, Long influencerId) {
			
		    Influencer influencer = influencerRepository.findById(influencerId).orElseThrow(() -> new RuntimeException("Influencer not found"));
	
		    return PostInfluencer.builder()
		            .post(post)
		            .influencer(influencer)
		            .build();
		}
	
    	
    	private PostResponseDTO entityToDTO(PostInfluencer postInfluencer) {
    		
    		PostResponseDTO postResponseDTO = PostResponseDTO.builder()
    				
			   .postTitle(postInfluencer.getPost().getTitle())
	           .postContent(postInfluencer.getPost().getContent())
	           .influencerName(postInfluencer.getInfluencer().getName())
	           .influencerContent(postInfluencer.getInfluencer().getContent())
	            
			   .build();
    		
    		return postResponseDTO;
    		
    	}
    	
}
