package com.youtubers.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youtubers.Service.InfluencerService;
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

    /* API 버전 ( @RequestBody 사용 )*/
    
	@PostMapping("/register")
//    public ResponseEntity<Influencer> createYoutuber(@RequestBody InfluencerDTO dto,  @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
	public ResponseEntity<Influencer> createInfluencer(@RequestBody InfluencerDTO dto) {
		
//        Influencer createdInfluencer = influencerService.createInfluencer(dto, imageFile);
        Influencer createdInfluencer = influencerService.createInfluencer(dto);
        
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