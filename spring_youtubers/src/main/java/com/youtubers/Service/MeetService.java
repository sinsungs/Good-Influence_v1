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
import com.youtubers.repository.MeetUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MeetService {
	
	private final MeetRepository meetRepository;
	private final MeetUserRepository UsermeetRepository;
    
    
    public Meet createMeet(MeetDTO dto) {
    	
    	Meet meet = dtoToEntity(dto);

        return meetRepository.save(meet);
    }


    public MeetUser registerMeet(MeetUser userMeet) {
    	
        return UsermeetRepository.save(userMeet);
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
            meet.setStartTime(dto.getStartTime());
            meet.setMaxPlayers(dto.getMaxPlayers());
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
        
        if (optionalMeet.isPresent()) {
            meetRepository.deleteById(meetId);
            return true;
        }
        
        return false; // Meet not found
    }
    
    
    // Mapper 구역 
	private Meet dtoToEntity(MeetDTO dto) {
		
        Meet meet = Meet.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .startTime(dto.getStartTime())
                .maxPlayers(dto.getMaxPlayers())
                .currentPlayers(dto.getCurrentPlayers())
                .region(dto.getRegion())
                .result(dto.getResult())
                .build();
		
		return meet;
	}
	
	private MeetDTO entityToDTO(Meet meet) {
		
		MeetDTO meetDTO = MeetDTO.builder()
				.meetid(meet.getMeetid())
	            .title(meet.getTitle())
                .content(meet.getContent())
                .startTime(meet.getStartTime())
                .maxPlayers(meet.getMaxPlayers())
                .currentPlayers(meet.getCurrentPlayers())
                .region(meet.getRegion())
                .result(meet.getResult())
				.build();
		
		return meetDTO;
		
	}
    

}
