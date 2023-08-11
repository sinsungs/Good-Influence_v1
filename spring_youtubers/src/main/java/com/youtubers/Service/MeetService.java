package com.youtubers.Service;

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
        Meet meet = Meet.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .startTime(dto.getStartTime())
                .maxPlayers(dto.getMaxPlayers())
                .currentPlayers(dto.getCurrentPlayers())
                .build();

        return meetRepository.save(meet);
    }


    public MeetUser registerMeet(MeetUser userMeet) {
    	
        return UsermeetRepository.save(userMeet);
    }
    
    
    

}
