package com.youtubers.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youtubers.dto.MeetUserDTO;
import com.youtubers.entity.Meet;
import com.youtubers.entity.MeetUser;
import com.youtubers.entity.User;
import com.youtubers.repository.MeetRepository;
import com.youtubers.repository.MeetUserRepository;
import com.youtubers.repository.OrdersRepository;
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
	private final OrdersRepository ordersRepository;

	@Transactional
    public String registerMeet(MeetUserDTO dto) {
    	
        User user = userRepository.findByEmail(dto.getEmail()).orElse(null);
        Meet meet = meetRepository.findById(dto.getMeetid()).orElse(null);
        
//        ordersRepository.save(null);
        //주문내역 추가해야함 
        
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
    

	    public String cancelApplication(Long applicationId) {
	        // Find the application to cancel
	        Optional<MeetUser> optionalMeetUser = meetUserRepository.findById(applicationId);
	        
//	        ordersRepository.save(null);
	        //주문 취소 내역 추가해야함 
	
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
