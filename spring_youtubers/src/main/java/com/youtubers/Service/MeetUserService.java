package com.youtubers.Service;

import java.sql.Timestamp;
import java.util.Optional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youtubers.dto.MeetUserDTO;
import com.youtubers.entity.Meet;
import com.youtubers.entity.MeetUser;
import com.youtubers.entity.Orders;
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
        
        if (user == null || meet == null) {
            return "사용자 또는 모임을 찾을 수 없습니다.";
        }
        
        if(user.getAmount() < 10000) {
        	return "보유금이 부족합니다";
        }
        
        MeetUser meetUser = meetUserRepository.findByUserAndMeet(user, meet);
        
        if (meetUser != null) {
            return "이미 참가중입니다.";
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
        		

        // 주문내역에 추가하고 보유금 차감해야함 
        Orders orders = Orders.builder()
        		.price(dto.getPrice())
        		.user(user)
        		.meet(meet)
        		.build();
        
        ordersRepository.save(orders);
        
    	
        user.setAmount(user.getAmount() - dto.getPrice());
        userRepository.save(user);
        
      
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

        
        return "모임을 참여했습니다.";
        
    }
    
		@Transactional
	    public String deleteMeeting(MeetUserDTO dto) {
	    	
	        User user = userRepository.findByEmail(dto.getEmail()).orElse(null);
	        Meet meet = meetRepository.findById(dto.getMeetid()).orElse(null);
	        
	        if (user == null || meet == null) {
	            return "사용자 또는 모임을 찾을 수 없습니다.";
	        }
	        
//	        if (!meetUserRepository.existsByUserAndMeet(user, meet)) {
//	            return "참가하고 있지 않은 모임입니다.";
//	        }
	        
	        MeetUser meetUser = meetUserRepository.findByUserAndMeet(user, meet);

	        if (meetUser == null) {
	            return "해당 사용자는 이 모임에 참가하지 않았습니다.";
	        }
	        
	        meetUserRepository.delete(meetUser);
	        
	        // 주문 내역 삭제
	        ordersRepository.deleteByUserAndMeet(user, meet);
	        
	        user.setAmount(user.getAmount() + dto.getPrice());
	        userRepository.save(user);
	        
//	        // 로직 추가 
//	        // currentPlayers 값 감소
	        meet.setCurrentPlayers(meet.getCurrentPlayers() - 1);

	        meetRepository.save(meet); // 업데이트된 Meet 객체 저장
	        
	
	        return "모임을 취소했습니다.";
	    }
    
    

}
