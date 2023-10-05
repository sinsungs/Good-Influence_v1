package com.youtubers.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtubers.Service.StatisticsService;
import com.youtubers.dto.InfluencerStatisticsDTO;
import com.youtubers.dto.RankDTO;
import com.youtubers.dto.RankMeetDTO;
import com.youtubers.dto.RankRecommendDTO;
import com.youtubers.entity.Influencer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {
	
	private final StatisticsService statisticsService;
	
	// 미팅과 추천 많이 받은 순위로 매기기 
	@GetMapping("/ranking")
	public ResponseEntity<?> influencerRank() {
		
		// 미팅 순위
		List<RankMeetDTO> meetRanks = statisticsService.findTop5UsersWithMostMeets();
		
		// 추천 순위 
		List<RankRecommendDTO> recommendRank = statisticsService.findTop5UsersWithMostRecommend();
		
        RankDTO dto = new RankDTO();
        dto.setMeetRanks(meetRanks);
        dto.setRecommendRank(recommendRank);

		return ResponseEntity.ok(dto);
		
		
	}
	

	@GetMapping("/influencer")
	public ResponseEntity<?> influenceStatistics() {
		
		// 등록된 인플루언서 수 조회 
		int influenceCount = statisticsService.influenceCount();
		
		// 등록된 인플루언서 카테고리 수 조회
		int categoryCount = statisticsService.categoryCount();
		
		// 등록된 인플루언서 추천 게시글 수 조회 
		int postCount = statisticsService.postCount();
		
		// 등록된 모임 수 조회 
		int meetCount = statisticsService.meetCount();
		
		// 등록된 모임에 신청한 총 수 조회 
		int meetUserCount = statisticsService.meetUserCount();
		
		// 유저들에 총 결제금액 
		int totalDeposit = statisticsService.paymentCount();
		
		// 오늘의 유저들 총 결제금액 
//		Double totalDepositToday = statisticsService.totalDepositToday();
		
		// 추천 랭킹 인플루언서 조회
//		int ranking = adminService.ranking();
		
        InfluencerStatisticsDTO dto = new InfluencerStatisticsDTO();
        
        dto.setInfluenceCount(influenceCount);
        dto.setCategoryCount(categoryCount);
        dto.setPostCount(postCount);
        dto.setMeetCount(meetCount);
        dto.setMeetUserCount(meetUserCount);
        dto.setTotalDeposit(totalDeposit);
//        dto.setTotalDepositToday(totalDepositToday);
		
		return ResponseEntity.ok(dto);
	}
	

	
}
