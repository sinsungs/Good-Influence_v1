package com.youtubers.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.youtubers.dto.InfluencerDTO;
import com.youtubers.dto.PostRequestDTO;
import com.youtubers.dto.PostResponseDTO;
import com.youtubers.entity.Influencer;
import com.youtubers.entity.Post;
import com.youtubers.entity.PostInfluencer;
import com.youtubers.repository.InfluencerRepository;
import com.youtubers.repository.PostRepository;
import com.youtubers.repository.PostInfluencerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostInfluencerService{

	private final PostRepository postRepository;
	private final InfluencerRepository influencerRepository;
	private final PostInfluencerRepository postInfluencerRepository;
	
//    public List<PostResponseDTO> createInfluencerPost(PostRequestDTO dto) {
      public PostResponseDTO createInfluencerPost(PostRequestDTO dto) {
        
        PostInfluencer postInfluencer = dtoToEntity(dto);
        
        postInfluencerRepository.save(postInfluencer);

        return entityToDTO(postInfluencer);
        
    }
    
    public List<PostResponseDTO> getList() {
        List<PostInfluencer> postInfluencers = postInfluencerRepository.findAll();
        List<PostResponseDTO> dtoList = new ArrayList<>();
        
        for (PostInfluencer postInfluencer : postInfluencers) {
            dtoList.add(PostResponseDTO.of(postInfluencer));
        }
        
        return dtoList;
    }
    
    
    
    	PostInfluencer dtoToEntity(PostRequestDTO dto) {
    		
	        Post post = postRepository.findById(dto.getPno()).orElseThrow(() -> new RuntimeException("Post not found"));
	        Influencer influencer = influencerRepository.findById(dto.getIno()).orElseThrow(() -> new RuntimeException("Influencer not found"));
		        
	        PostInfluencer postInfluencer = PostInfluencer.builder()
	                .post(post)
	                .influencer(influencer)
	                .build();
	        
	        return postInfluencer;
    }
    	
    	PostResponseDTO entityToDTO(PostInfluencer postInfluencer) {
    		
    		PostResponseDTO postResponseDTO = PostResponseDTO.builder()
    				
			   .postTitle(postInfluencer.getPost().getTitle())
	           .postContent(postInfluencer.getPost().getContent())
	           .influencerName(postInfluencer.getInfluencer().getName())
	           .influencerContent(postInfluencer.getInfluencer().getContent())
	            
			   .build();
    		
    		return postResponseDTO;
    		
    	}
    	
}
