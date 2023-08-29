package com.youtubers.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youtubers.dto.MeetUserDTO;
import com.youtubers.entity.Meet;
import com.youtubers.entity.MeetUser;
import com.youtubers.entity.User;
import com.youtubers.repository.MeetRepository;
import com.youtubers.repository.MeetUserRepository;
import com.youtubers.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MeetUserService {
	
	private final UserRepository userRepository;
	private final MeetRepository meetRepository;
	private final MeetUserRepository meetUserRepository;

	@Transactional
    public String registerMeet(MeetUserDTO dto) {
    	
        User user = userRepository.findById(dto.getUserid()).orElse(null);
        Meet meet = meetRepository.findById(dto.getMeetid()).orElse(null);
        
        if (meetUserRepository.existsByUserAndMeet(user, meet)) {
            return "이미 참가한 모임입니다.";
        }
        
        // 신청 마감 처리 
        if (meet.getCurrentPlayers() >= meet.getMaxPlayers()) {
            return "이미 모임이 가득 찼습니다.";
        }
        
        MeetUser meetuser = MeetUser.builder()
        		.user(user)
        		.meet(meet)
        		.build();
    	
        meetUserRepository.save(meetuser);
        
        // 로직 추가 
        // currentPlayers 값 증가
        meet.setCurrentPlayers(meet.getCurrentPlayers() + 1);

        // currentPlayers와 maxPlayers 비교하여 result 값 설정
        if (meet.getCurrentPlayers() >= meet.getMaxPlayers()) {
            meet.setResult("마감");
        } else {
            meet.setResult("신청가능");
        }

        meetRepository.save(meet); // 업데이트된 Meet 객체 저장

//        System.out.println(result);
        return "성공했습니다";
        
    }
    
    public List<MeetUserDTO> getApplicationHistory(Long userId) {
    	
        List<MeetUser> applicationHistory = meetUserRepository.findByUserUserId(userId);
        List<MeetUserDTO> historyDTOs = new ArrayList<>();

        for (MeetUser meetUser : applicationHistory) {
            MeetUserDTO dto = new MeetUserDTO();
//            dto.setId(meetUser.getUmid());
            dto.setUserid(meetUser.getUser().getId());
            dto.setMeetid(meetUser.getMeet().getMeetid());
            // You can populate other fields as needed
            historyDTOs.add(dto);
        }

        return historyDTOs;
    }

	
	    public String cancelApplication(Long applicationId) {
	        // Find the application to cancel
	        Optional<MeetUser> optionalMeetUser = meetUserRepository.findById(applicationId);
	
	        if (optionalMeetUser.isPresent()) {
	            MeetUser meetUser = optionalMeetUser.get();
	            // Implement the cancellation process
	            meetUserRepository.delete(meetUser);
	
	            return "Application cancelled successfully!";
	        } else {
	            return null;
	        }
	    }
    
    

}
