package com.youtubers.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.youtubers.dto.MeetDTO;
import com.youtubers.entity.Meet;
import com.youtubers.entity.MeetUser;
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
    
    
	private Meet dtoToEntity(MeetDTO dto) {
		
        Meet meet = Meet.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .startTime(dto.getStartTime())
                .maxPlayers(dto.getMaxPlayers())
                .currentPlayers(dto.getCurrentPlayers())
                .region(dto.getRegion())
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
