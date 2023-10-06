package com.youtubers.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youtubers.dto.InfluencerDTO;
import com.youtubers.dto.MeetUserDTO;
import com.youtubers.entity.Influencer;
import com.youtubers.entity.Meet;
import com.youtubers.entity.MeetUser;
import com.youtubers.entity.Orders;
import com.youtubers.entity.User;
import com.youtubers.repository.InfluencerRepository;
import com.youtubers.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class InfluencerService{

	private final InfluencerRepository influencerRepository;
	private final UserRepository userRepository;
	
    @Value("${upload.dir}")
    private String uploadDir;
	
    
// 유저 인플루언서 인증
	@Transactional
	public String verifyInfluencer(Long ino, String email) {
		
        User user = userRepository.findByEmail(email).orElse(null);
        Influencer findInfluencer = influencerRepository.findById(ino).orElse(null);
        
        System.out.println(user.getSns());
        
//        System.out.println(user.getEmail());
        
        if (!"인플루언서".equals(user.getSns())) {
        	return "관리자에게 권한을 받고 요청해주세요.";
        }
        
        if (user == null || findInfluencer == null) {
            return "다시 시도해주세요.";
        }
        
//        Influencer influencers = influencerRepository.findById(user.getId()).orElse(null);
        Influencer influencer = influencerRepository.findByUser(user);
        
        if(influencer != null) {
        	return "이미 인증된 유저입니다.";
        }
        
        findInfluencer.setUser(user);
        
        influencerRepository.save(findInfluencer);
        
		return "인증에 성공했습니다.";
	}
	
	
// 인플루언서 등록 
	public Influencer createInfluencer(InfluencerDTO dto, String uploadedFileName) {
		
//		Influencer findInfluencer = influencerRepository.findById()
    	User user = userRepository.findByUsername("관리자").orElse(null);
    	
		Influencer influencer = dtoToEntity(dto);
		influencer.setImageUrl(uploadedFileName);
		influencer.setUser(user);
		
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

		Influencer influencer = Influencer.builder()
				.ino(dto.getIno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.likes(0)
				.name(dto.getName())
				.sns(dto.getSns())
				.category(dto.getCategory())
//				.user()
				.build();
		
		return influencer;
	}
	

	InfluencerDTO entityToDTO(Influencer influencer) {
		
		InfluencerDTO influencerDTO = InfluencerDTO.builder()
	            .ino(influencer.getIno())
	            .name(influencer.getName())
	            .title(influencer.getTitle())
	            .content(influencer.getContent())
	            .likes(influencer.getLikes())
	            .sns(influencer.getSns())
	            .category(influencer.getCategory())
	            .image_url(influencer.getImageUrl())
	            .verifyuser(influencer.getUser().getUsername())
				.build();
		
		return influencerDTO;
		
	}


}
