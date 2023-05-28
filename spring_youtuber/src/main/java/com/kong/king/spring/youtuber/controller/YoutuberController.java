package com.kong.king.spring.youtuber.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kong.king.spring.youtuber.dto.BoardDTO;
import com.kong.king.spring.youtuber.dto.PageRequestDTO;
import com.kong.king.spring.youtuber.dto.ReplyDTO;
import com.kong.king.spring.youtuber.dto.YoutuberDTO;
import com.kong.king.spring.youtuber.entity.Youtuber;
import com.kong.king.spring.youtuber.service.YoutuberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/youtuber")
@Log4j2
@RequiredArgsConstructor
public class YoutuberController {

    private final YoutuberService youtuberService;

    // Create a new Youtuber
	@PostMapping("/register")
    public ResponseEntity<Youtuber> createYoutuber(@RequestBody YoutuberDTO dto) {
        Youtuber createdYoutuber = youtuberService.createYoutuber(dto);
        return ResponseEntity.ok(createdYoutuber);
    }
	
//    @GetMapping("/list")
//    public ResponseEntity<List<Youtuber>> getAllYoutubers() {
//        List<Youtuber> youtubers = youtuberService.getAllYoutubers();
//        return new ResponseEntity<>(youtubers, HttpStatus.OK);
//    }
	
//  @GetMapping("/list")
//  public ResponseEntity<List<Youtuber>> getAllYoutubers() {
//      List<Youtuber> youtubers = youtuberService.getAllYoutubers();
//      return ResponseEntity.ok(youtubers);
//  }
	
	
    
    @GetMapping({"/read/{yno}", "/modify/{yno}"})
    public ResponseEntity<Object> getYoutuberWithWriter(@PathVariable("yno") Long yno) {
    	YoutuberDTO youtuberWithWriter = youtuberService.getYoutuberWithWriter(yno);
        return new ResponseEntity<>(youtuberWithWriter, HttpStatus.OK);
    }
    
    
//	@GetMapping({"/read", "/modify"})
//	public void read(Long yno, Model model) {
//		log.info("yno: " + yno);
//		
//		YoutuberDTO youtuberDTO = youtuberService.get(yno);
//		
//		log.info(youtuberDTO);
//		
////		model.addAttribute("dto", youtuberDTO);
//	}
    
    
	@PutMapping("/update")
	public ResponseEntity<String> modify(@RequestBody YoutuberDTO dto){
		
		log.info(dto);
		
		youtuberService.modify(dto);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	// yno값이 자동으로 안들어가고 주소창 yno가 dto바로 적용되는 구문을 찾아야함 
	// 아니다 이코드가 맞다 hidden으로 yno을 뷰단에서 받아서 자동으로 처리하는게 맞다 
	
	@DeleteMapping("/delete/{yno}")
	public ResponseEntity<String> remove(@PathVariable("yno") Long yno){
		
		log.info("RNO : " + yno);
		
		youtuberService.remove(yno);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}