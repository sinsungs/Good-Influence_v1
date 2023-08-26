package com.youtubers.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
//    @GetMapping({"/read/{yno}", "/modify/{yno}"})
//    public ResponseEntity<Object> getYoutuberWithWriter(@PathVariable("yno") Long yno) {
//    	YoutuberDTO youtuberWithWriter = youtuberService.getYoutuberWithWriter(yno);
//        return new ResponseEntity<>(youtuberWithWriter, HttpStatus.OK);
//    }
    
//	@PutMapping("/update")
//	public ResponseEntity<String> modify(@RequestBody YoutuberDTO dto){
//		
//		log.info(dto);
//		
//		youtuberService.modify(dto);
//		
//		return new ResponseEntity<>("success", HttpStatus.OK);
//	}
//	
//	// yno값이 자동으로 안들어가고 주소창 yno가 dto바로 적용되는 구문을 찾아야함 
//	// 아니다 이코드가 맞다 hidden으로 yno을 뷰단에서 받아서 자동으로 처리하는게 맞다 
    
//	@DeleteMapping("/delete/{yno}")
//	public ResponseEntity<String> remove(@PathVariable("yno") Long yno){
//		
//		log.info("RNO : " + yno);
//		
//		youtuberService.remove(yno);
//		
//		return new ResponseEntity<>("success", HttpStatus.OK);
//	}
    
    
 
	
}