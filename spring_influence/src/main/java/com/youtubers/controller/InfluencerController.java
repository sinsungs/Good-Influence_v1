package com.youtubers.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.youtubers.Service.InfluencerService;
import com.youtubers.Service.S3UploadService;
import com.youtubers.dto.InfluencerDTO;
import com.youtubers.entity.Influencer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/influencer")
@Log4j2
@RequiredArgsConstructor
public class InfluencerController {

    private final InfluencerService influencerService;
    private final S3UploadService s3UploadService;

    /* API 버전 ( @RequestBody 사용 )*/
    
    @PostMapping("/verify/{ino}")
    public ResponseEntity<String> verifyInfluencer(@PathVariable Long ino, Authentication authentication) {
    	
    	String email = authentication.getName();
    	
        String result = influencerService.verifyInfluencer(ino, email);

            return ResponseEntity.ok(result);

    }
    
    
	@PostMapping("/register")
	public ResponseEntity<Influencer> createInfluencer( @RequestPart InfluencerDTO dto,  @RequestPart("file") MultipartFile file) throws IOException {
		
        String uploadedFileName = s3UploadService.saveFile(file);
        
        // 파일 업로드 로직 추가
//        String uploadedFileName = "./img/" + file.getOriginalFilename();
        
        Influencer createdInfluencer = influencerService.createInfluencer(dto, uploadedFileName);
        
        return ResponseEntity.ok(createdInfluencer);
    }
	
    
	@GetMapping("/list")
	public ResponseEntity<List<InfluencerDTO>> listInfluencer() {
		

		List<InfluencerDTO> listInfluencer = influencerService.listInfluencer();
		
		return ResponseEntity.ok(listInfluencer);
	}
	
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Influencer> updateInfluencer(@PathVariable Long id, @RequestBody InfluencerDTO dto) {
    	
        Influencer updatedInfluencer = influencerService.updateInfluencer(id, dto);
        
        if (updatedInfluencer != null) {
        	
            return ResponseEntity.ok(updatedInfluencer);
            
        } else {
        	
            return ResponseEntity.notFound().build();
        }
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInfluencer(@PathVariable Long id) {
    	
        boolean deleted = influencerService.deleteInfluencer(id);
        
        if (deleted) {
        	
            return ResponseEntity.noContent().build();
        } else {
        	
            return ResponseEntity.notFound().build();
        }
    }
 
	
}
