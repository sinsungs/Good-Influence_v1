package com.youtubers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youtubers.Service.MeetService;
import com.youtubers.dto.MeetDTO;
import com.youtubers.entity.Meet;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetController {

    private final MeetService meetService;
	
    @PostMapping("/create")
    public ResponseEntity<Meet> createMeet(@RequestBody MeetDTO dto) {
    	
        Meet createdMeet = meetService.createMeet(dto);
        
        return ResponseEntity.ok(createdMeet);
    }
    
    
    
//    @GetMapping("/list")
//    public ResponseEntity<List<PostYoutuberDTO>> listPost() {
//    	
//        List<PostYoutuberDTO> response = postyoutuberService.getList();
//        return ResponseEntity.ok(response);
//        
//    }
}
