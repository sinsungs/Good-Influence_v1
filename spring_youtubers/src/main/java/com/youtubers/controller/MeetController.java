package com.youtubers.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Meet> createMeet(@RequestBody MeetDTO dto, Authentication authentication) {
    	
    	System.out.println("정보" + authentication.getName());
    	
    	dto.setWriter(authentication.getName());
    	
        Meet createdMeet = meetService.createMeet(dto);
        
        return ResponseEntity.ok(createdMeet);
    }
    
    
    @GetMapping("/list")
    public ResponseEntity<List<MeetDTO>> listMeet() {
    	
        List<MeetDTO> listMeet = meetService.listMeet();
        
        return ResponseEntity.ok(listMeet);
    }
    
    
    @PutMapping("/update/{meetId}")
    public ResponseEntity<String> updateMeet(@PathVariable Long meetId, @RequestBody MeetDTO dto) {
    	
        String updatedMeet = meetService.updateMeet(meetId, dto);
        
        if (updatedMeet == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(updatedMeet);
    }
    

    @DeleteMapping("/delete/{meetId}")
    public ResponseEntity<Void> deleteMeet(@PathVariable Long meetId) {
        boolean deleted = meetService.deleteMeet(meetId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    
    
//    @GetMapping("/list")
//    public ResponseEntity<List<PostYoutuberDTO>> listPost() {
//    	
//        List<PostYoutuberDTO> response = postyoutuberService.getList();
//        return ResponseEntity.ok(response);
//        
//    }
}
