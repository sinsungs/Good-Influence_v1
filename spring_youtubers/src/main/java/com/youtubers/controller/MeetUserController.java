package com.youtubers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youtubers.Service.MeetUserService;
import com.youtubers.dto.MeetUserDTO;
import com.youtubers.entity.MeetUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetUserController {

    private final MeetUserService meetuserService;
	
    
//    @PostMapping("/register")
//    public ResponseEntity<MeetUser>	registerMeet(@RequestBody MeetUserDTO dto) {
//    	
//    	MeetUser registerMeet = meetuserService.registerMeet(dto);
//        
//        return ResponseEntity.ok(registerMeet);
//    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerMeet(@RequestBody MeetUserDTO dto) {
    	
//    	MeetUser registerMeet ;
    	
    	String sss = meetuserService.registerMeet(dto);
        
        return ResponseEntity.ok(sss);
    }
    

    
    
    
//    @GetMapping("/list")
//    public ResponseEntity<List<PostYoutuberDTO>> listPost() {
//    	
//        List<PostYoutuberDTO> response = postyoutuberService.getList();
//        return ResponseEntity.ok(response);
//        
//    }
}
