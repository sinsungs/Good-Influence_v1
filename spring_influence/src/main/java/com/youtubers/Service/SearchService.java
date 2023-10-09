package com.youtubers.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.youtubers.dto.InfluencerDTO;
import com.youtubers.dto.SearchDTO;
import com.youtubers.entity.Influencer;
import com.youtubers.repository.InfluencerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class SearchService {
	
	private final InfluencerRepository influencerRepository;

	public List<SearchDTO> searchInfluencer(SearchDTO dto) {
//	    String query = dto.getName();
//	    String query = "%" + dto.getName() + "%"; // 검색어 앞뒤에 '%' 추가
	    String query = dto.getName() + "%"; 
	    
	    // 검색 쿼리를 활용하여 인플루언서 검색
	    List<Influencer> searchResults = influencerRepository.findByNameLike(query);
	    
	    List<SearchDTO> dtoList = new ArrayList<>();
        for (Influencer influencer : searchResults) {
            dtoList.add(entityToDTO(influencer));
        }
        
	    return dtoList;
	}
	
	

	SearchDTO entityToDTO(Influencer influencer) {
		
		SearchDTO searchDTO = SearchDTO.builder()
				.name(influencer.getName())     
				.ino(influencer.getIno())
				.build();
		
		return searchDTO;
		
	}
	
	Influencer dtoToEntity(InfluencerDTO dto) {


		Influencer influencer = Influencer.builder()
				.ino(dto.getIno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.likes(dto.getLikes())
				.name(dto.getName())
//				.writer(member)
				.build();
		
		return influencer;
	}
	

	
}
