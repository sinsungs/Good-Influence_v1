package com.youtubers.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.youtubers.dto.MeetDTO;
import com.youtubers.dto.RankMeetDTO;
import com.youtubers.dto.RankRecommendDTO;
import com.youtubers.entity.Influencer;
import com.youtubers.entity.Meet;
import com.youtubers.entity.User;
import com.youtubers.repository.InfluencerRepository;
import com.youtubers.repository.MeetRepository;
import com.youtubers.repository.MeetUserRepository;
import com.youtubers.repository.PaymentRepository;
import com.youtubers.repository.PostInfluencerRepository;
import com.youtubers.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatisticsService {
	
	private final InfluencerRepository influencerRepositoy;
	private final PostRepository postRepository;
	private final PostInfluencerRepository postInfluencerRepository;
	private final MeetRepository meetRepository;
	private final MeetUserRepository meetuserRepository;
	private final PaymentRepository paymentRepository;
	
	
	public List<RankMeetDTO> findTop5UsersWithMostMeets() {
		
	    List<Object[]> results = meetuserRepository.findTop5UsersWithMostMeets(); // 사용자 정보와 미팅 횟수 정보를 가져오는 쿼리 실행
	    
		int limit = 5;
		if (results.size() > limit) {
			results = results.subList(0, limit);
		}

	    List<RankMeetDTO> dtoList = new ArrayList<>();

	    for (Object[] result : results) {
	        User user = (User) result[0]; // User 객체를 가져옴
	        Long meetCount = (Long) result[1]; // 미팅 횟수 정보를 가져옴
	        
	        RankMeetDTO dto = entityToDTO(user); // User 정보를 DTO로 변환
	        dto.setMeetcount(meetCount.intValue()); // 미팅 횟수 정보를 DTO에 추가
	        dtoList.add(dto);
	    }

	    return dtoList;
	}
	
	
private RankMeetDTO entityToDTO(User user) {

	RankMeetDTO dto = RankMeetDTO.builder()
			.id(user.getId())
			.username(user.getUsername())
			.sns(user.getSns())
			.experience(user.getExperience())

			
			.build();
	
		return dto;
	}
//
//public List<RankMeetDTO> findTop5UsersWithMostMeets() {
//	
//    List<Object[]> results = meetuserRepository.findTop5UsersWithMostMeets(); // 사용자 정보와 미팅 횟수 정보를 가져오는 쿼리 실행
//
//    List<RankMeetDTO> dtoList = new ArrayList<>();
//
//    for (Object[] result : results) {
//        User user = (User) result[0]; // User 객체를 가져옴
//        Long meetCount = (Long) result[1]; // 미팅 횟수 정보를 가져옴
//        
//        RankMeetDTO dto = entityToDTO(user); // User 정보를 DTO로 변환
//        dto.setMeetcount(meetCount.intValue()); // 미팅 횟수 정보를 DTO에 추가
//        dtoList.add(dto);
//    }
//
//    return dtoList;
//}

	
	
	public List<RankRecommendDTO> findTop5UsersWithMostRecommend() {

//		List<Influencer> top5Influencers = postInfluencerRepository.findTop5UsersWithMostRecommend();
//		

//		
//		return top5Influencers;
		
	    List<Object[]> results = postInfluencerRepository.findTop5UsersWithMostRecommend(); // 사용자 정보와 미팅 횟수 정보를 가져오는 쿼리 실행
	    
		int limit = 5;
		if (results.size() > limit) {
			results = results.subList(0, limit);
		}

	    List<RankRecommendDTO> dtoList = new ArrayList<>();

	    for (Object[] result : results) {
	    	Influencer influencer = (Influencer) result[0]; // User 객체를 가져옴
	        Long recommendCount = (Long) result[1]; // 미팅 횟수 정보를 가져옴
	        
	        RankRecommendDTO dto = entityToDTOs(influencer); // User 정보를 DTO로 변환
	        dto.setRecommendcount(recommendCount.intValue()); // 미팅 횟수 정보를 DTO에 추가
	        dtoList.add(dto);
	    }

	    return dtoList;
	}
	
	private RankRecommendDTO entityToDTOs(Influencer influencer) {

		RankRecommendDTO dto = RankRecommendDTO.builder()
				.id(influencer.getIno())
				.name(influencer.getName())
				.sns(influencer.getSns())
				
				.build();
		
			return dto;
		}

	
	
	
	
	public int meetRank() {
		// TODO Auto-generated method stub
		return meetuserRepository.countMeetUser();
	}
	
	
	public int recommnedRank() {
		// TODO Auto-generated method stub
		return postInfluencerRepository.recommnedRank();
	}
	

	public int influenceCount() {
		// TODO Auto-generated method stub
		return influencerRepositoy.countAllInfluencers();
	}

	public int categoryCount() {
		// TODO Auto-generated method stub
		return influencerRepositoy.countInfluencersByCategory();
	}
	
	public int postCount() {
		// TODO Auto-generated method stub
		return postRepository.countAllPost();
	}

	public int meetCount() {
		// TODO Auto-generated method stub
		return meetRepository.countMeet();
	}

	public int meetUserCount() {
		// TODO Auto-generated method stub
		return meetuserRepository.countMeetUser();
	}
	
	public int paymentCount() {
		return paymentRepository.TotalDeposit();
	}
	
	public int ranking() {
		// TODO Auto-generated method stub
		return 0;
	}















//	public Double  totalDepositToday() {
//		// TODO Auto-generated method stub
//		return paymentRepository.TotalDepositToday();
//	}

}
