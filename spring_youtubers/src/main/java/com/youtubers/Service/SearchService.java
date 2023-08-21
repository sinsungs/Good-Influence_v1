package com.youtubers.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youtubers.dto.SearchDTO;
import com.youtubers.entity.Influencer;
import com.youtubers.repository.InfluencerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class SearchService {
	
	private final InfluencerRepository youtuberRepository;

	public List<Influencer> searchInfluencer(SearchDTO dto) {
	    String query = dto.getName();
	    
	    // 검색 쿼리를 활용하여 인플루언서 검색
	    List<Influencer> searchResults = youtuberRepository.findByNameLike(query);
	    
	    return searchResults;
	}
	
}
