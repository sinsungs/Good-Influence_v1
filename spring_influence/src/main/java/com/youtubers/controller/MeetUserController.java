package com.youtubers.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youtubers.Service.MeetUserService;
import com.youtubers.dto.MeetUserDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetUserController {

    private final MeetUserService meetuserService;
	
 
    @PostMapping("/register/{id}")
    public ResponseEntity<String> registerMeeting(@PathVariable Long id, @RequestBody MeetUserDTO dto,  Authentication authentication) {
    	
//    	MeetUser registerMeet ;
    	System.out.println("결제금액" + dto.getPrice());
    
    	
    	dto.setEmail(authentication.getName());
    	dto.setMeetid(id);
    	dto.setPrice(dto.getPrice());
    	
    	String sss = meetuserService.registerMeet(dto);
        
        return ResponseEntity.ok(sss);
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMeeting(@PathVariable Long id, MeetUserDTO dto,  Authentication authentication) {
    	
    	dto.setEmail(authentication.getName());
    	dto.setMeetid(id);
    	dto.setPrice(10000);
    	
        String result = meetuserService.deleteMeeting(dto);
        
        return ResponseEntity.ok(result);
    }
    
    
}
