package com.youtubers.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.youtubers.dto.MeetDTO;
import com.youtubers.entity.Meet;
import com.youtubers.entity.MeetUser;
import com.youtubers.entity.User;
import com.youtubers.repository.MeetRepository;
import com.youtubers.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MeetService {
	
	private final UserRepository userRepository;
	private final MeetRepository meetRepository;
	
    
    public Meet createMeet(MeetDTO dto) {
    	
    	User user = userRepository.findByEmail(dto.getWriter()).orElse(null);
    	
    	Meet meet = dtoToEntity(dto, user);

        return meetRepository.save(meet);
    }

    
    public List<MeetDTO> listMeet() {
    	
        List<Meet> meets = meetRepository.findAll();
        
        List<MeetDTO> dtoList = new ArrayList<>();
        
	     for (Meet meet : meets) {
	      dtoList.add(entityToDTO(meet));
	     }
	  
	     return dtoList;
    	
    }
    
    
    public String updateMeet(Long meetId, MeetDTO dto) {
        Meet meet = meetRepository.findById(meetId).orElse(null);
        
        if (meet != null) {
            meet.setTitle(dto.getTitle());
            meet.setContent(dto.getContent());
            meet.setMaxPlayers(dto.getMaxplayers());
            meet.setCurrentPlayers(dto.getCurrentPlayers());
            meet.setRegion(dto.getRegion());
            meet.setResult(dto.getResult());
            
            // Update other properties
            
            meetRepository.save(meet);
            return "수정 완료"; 
        }

        return null; // Meet not found
    }

    
    public boolean deleteMeet(Long meetId) {
    	
        Optional<Meet> optionalMeet = meetRepository.findById(meetId);
        
//        if (optionalMeet.isPresent()) {
//            meetRepository.deleteById(meetId);
//            return true;
//        }
//        
        if (optionalMeet.isPresent()) {
            Meet meet = optionalMeet.get();
            
            // Meet에 참여한 사용자들 가져오기
            List<MeetUser> meetUsers = meet.getMeetUsers();
            
            // 보유금을 돌려주는 로직 구현
            for (MeetUser meetUser : meetUsers) {
                User user = meetUser.getUser();
                
    	        user.setAmount(user.getAmount() + 10000);
    	        userRepository.save(user);
            }
            
            meetRepository.delete(meet);
            return true;
        }
        
        return false; // Meet not found
    }
    
    
    // Mapper 구역 
	private Meet dtoToEntity(MeetDTO dto, User user) {
		
        Meet meet = Meet.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .maxPlayers(dto.getMaxplayers())
                .currentPlayers(dto.getCurrentPlayers())
                .region(dto.getRegion())
                .result(dto.getResult())
                .meettime(dto.getMeettime())
                .result("신청가능")
                .user(user)
                .build();
		
		return meet;
	}
	
	private MeetDTO entityToDTO(Meet meet) {
		
		MeetDTO meetDTO = MeetDTO.builder()
				.meetid(meet.getMeetid())
	            .title(meet.getTitle())
                .content(meet.getContent())
                .maxplayers(meet.getMaxPlayers())
                .currentPlayers(meet.getCurrentPlayers())
                .region(meet.getRegion())
                .result(meet.getResult())
                .meettime(meet.getMeettime())
                .writer(meet.getUser().getUsername())
				.build();
		
		return meetDTO;
		
	}
    

}
