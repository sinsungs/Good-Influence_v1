package com.youtubers.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
	
 
    
    @PostMapping("/register")
    public ResponseEntity<String> registerMeet(@RequestBody MeetUserDTO dto) {
    	
//    	MeetUser registerMeet ;
    	
    	String sss = meetuserService.registerMeet(dto);
        
        return ResponseEntity.ok(sss);
    }
    
    
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<MeetUserDTO>> getApplicationHistory(@PathVariable Long userId) {
    	
        List<MeetUserDTO> applicationHistory = meetuserService.getApplicationHistory(userId);
        
        return ResponseEntity.ok(applicationHistory);
    }

    
    @DeleteMapping("/cancel/{applicationId}")
    public ResponseEntity<String> cancelApplication(@PathVariable Long applicationId) {
    	
        String result = meetuserService.cancelApplication(applicationId);
        
        if (result != null) {
        	
            return ResponseEntity.ok(result);
            
        } else {
        	
            return ResponseEntity.notFound().build();
        }
        
    }
    

    
    
}
