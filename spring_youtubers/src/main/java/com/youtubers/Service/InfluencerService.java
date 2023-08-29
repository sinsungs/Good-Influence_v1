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
import com.youtubers.dto.MeetDTO;
import com.youtubers.entity.Influencer;
import com.youtubers.entity.Meet;
import com.youtubers.repository.InfluencerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class InfluencerService{

	private final InfluencerRepository influencerRepository;
	
    @Value("${upload.dir}")
    private String uploadDir;
	
// 인플루언서 등록 
//	public Influencer createInfluencer(InfluencerDTO dto,  MultipartFile imageFile) throws IOException {
	public Influencer createInfluencer(InfluencerDTO dto) {
		
//        if (imageFile != null && !imageFile.isEmpty()) {
//        	
//	   		 String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
//	         Path filePath = Paths.get(uploadDir, fileName);
//	         Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//	         dto.setImage_url(filePath.toString()); // 이미지 파일 경로를 엔티티에 저장
//        }
		
		Influencer influencer = dtoToEntity(dto);
		
		return influencerRepository.save(influencer); 
		
	}
	
    public List<InfluencerDTO> listInfluencer() {
    	
        List<Influencer> Influencers = influencerRepository.findAll();
        
        List<InfluencerDTO> dtoList = new ArrayList<>();
        
	     for (Influencer influencer : Influencers) {
	      dtoList.add(entityToDTO(influencer));
	     }
	  
	     return dtoList;
    	
    }

	
    public Influencer updateInfluencer(Long id, InfluencerDTO dto) {

        Influencer existingInfluencer = influencerRepository.findById(id).orElse(null);

        if (existingInfluencer != null) {

            existingInfluencer.setName(dto.getName());
            existingInfluencer.setTitle(dto.getTitle());
            existingInfluencer.setContent(dto.getContent());
            existingInfluencer.setLikes(dto.getLikes());

            Influencer updatedInfluencer = influencerRepository.save(existingInfluencer);
            
            return updatedInfluencer;
            
        } else {
        	
            return null;
        }
    }
    

    public boolean deleteInfluencer(Long id) {
        // Check if influencer exists
        Influencer existingInfluencer = influencerRepository.findById(id).orElse(null);

        if (existingInfluencer != null) {
            // Delete the influencer
            influencerRepository.delete(existingInfluencer);
            return true;
        } else {
            return false;
        }
    }
    
    
    // Mapper 정리 
	Influencer dtoToEntity(InfluencerDTO dto) {
//		Member member = Member.builder()
//				.email(dto.getWriterEmail()).build();

		Influencer influencer = Influencer.builder()
				.ino(dto.getIno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.likes(dto.getLikes())
				.name(dto.getName())
				.sns(dto.getSns())
				.category(dto.getCategory())
//				.writer(member)
				.build();
		
		return influencer;
	}
	
//	InfluencerDTO entityToDTO(Influencer youtuber, Member member) {
	InfluencerDTO entityToDTO(Influencer influencer) {
		
		InfluencerDTO influencerDTO = InfluencerDTO.builder()
	            .ino(influencer.getIno())
	            .name(influencer.getName())
	            .title(influencer.getTitle())
	            .content(influencer.getContent())
	            .likes(influencer.getLikes())
	            .sns(influencer.getSns())
	            .category(influencer.getCategory())
//				.writerEmail(member.getEmail())
//				.writerName(member.getName())
	            
				.build();
		
		return influencerDTO;
		
	}
}
