package com.youtubers.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.youtubers.Service.SearchService;
import com.youtubers.dto.SearchDTO;
import com.youtubers.entity.Influencer;

import lombok.RequiredArgsConstructor;

@Controller
//@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
	
    private final SearchService searchService;
    
    @PostMapping("/search")
    public  ResponseEntity<List<SearchDTO>> createPost(@RequestBody SearchDTO dto) {
    	
    	System.out.print(dto);
    	
    	List<SearchDTO> response = searchService.searchInfluencer(dto);
    	
        return ResponseEntity.ok(response);
    }
  
}
    
    