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
import com.youtubers.Service.S3UploadService;
import com.youtubers.dto.MeetDTO;
import com.youtubers.entity.Meet;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetController {

    private final MeetService meetService;
    private final S3UploadService s3UploadService;
	
    @PostMapping("/create")
    public ResponseEntity<Meet> createMeet(@RequestBody MeetDTO dto, Authentication authentication) {
    	
    	System.out.println("정보" + authentication.getName());
    	System.out.println("수용인원" + dto.getMaxplayers());
    	System.out.println("만남시간" + dto.getMeettime());
    	
//        String storedFileName = s3UploadService.saveFile(image,"images");
//        diary.setImageUrl(storedFileName);
    	
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
    public ResponseEntity<String> deleteMeet(@PathVariable Long meetId, Authentication authentication) {
    	
        boolean deleted = meetService.deleteMeet(meetId);
        
        String result = "삭제했습니다.";
        
        if (deleted) {
            return ResponseEntity.ok(result);
        }
        
        return ResponseEntity.notFound().build();
    }
    
    
    
}
