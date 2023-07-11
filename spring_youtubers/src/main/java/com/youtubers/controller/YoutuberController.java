package com.youtubers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youtubers.Service.YoutuberService;
import com.youtubers.dto.YoutuberDTO;
import com.youtubers.entity.Youtuber;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/youtuber")
@Log4j2
@RequiredArgsConstructor
public class YoutuberController {

    private final YoutuberService youtuberService;

    /* API 버전 ( @RequestBody 사용 )*/
    
	@PostMapping("/register")
    public ResponseEntity<Youtuber> createYoutuber(@RequestBody YoutuberDTO dto) {
        Youtuber createdYoutuber = youtuberService.createYoutuber(dto);
        return ResponseEntity.ok(createdYoutuber);
    }
    
//  @GetMapping("/list")
//  public ResponseEntity<List<YoutuberDTO>> getAllYoutubersWithWriters() {
//      List<YoutuberDTO> youtubers = youtuberService.getAllYoutubersWithWriters();
//      return ResponseEntity.ok(youtubers);
//  }
    
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
    
    
    /* ============================================================   */
    
//	@GetMapping("/register")
//	public void register() {
//		log.info("register get..........");
//	}
	
    
//	@PostMapping("/register")
//	public String createYoutuber(YoutuberDTO dto) {
//		
//		youtuberService.createYoutuber(dto);
//		
//		return "redirect:/youtuber/list";
//	}
	
//	@GetMapping("/list")
//	public void getAllYoutubersWithWriters(Model model) {
//	    List<YoutuberDTO> youtubers = youtuberService.getAllYoutubersWithWriters();
//	    model.addAttribute("youtubers", youtubers);
//	}
	
	
	
//    @GetMapping({"/read", "/modify"})
//    public void getYoutuberWithWriter(@RequestParam("yno") Long yno, Model model) {
//    	
//    	YoutuberDTO youtuberDTO = youtuberService.getYoutuberWithWriter(yno);
//    	model.addAttribute("dto", youtuberDTO);
//    	
//    }
    
//    @PostMapping("/modify")
//	public String modify(YoutuberDTO dto, RedirectAttributes redirectAttributes){
//		
//		log.info(dto);
//		
//		youtuberService.modify(dto);
//		
//		redirectAttributes.addAttribute("yno", dto.getYno());
//		
////		redirectAttributes 리디렉션 URL에 'yno' 매개변수를 쿼리 매개변수로 추가하는 역할을 합니다
//		
//		return "redirect:/youtuber/read";
//	}
	
	
//    @PostMapping("/remove")
//    public String remove(@RequestParam("yno") Long yno){
//
//    	log.info("YNO : " + yno);
//
//    	youtuberService.remove(yno);
//
//    	return "redirect:/youtuber/list";
//    }
	
//	@PostMapping("/remove")
//	public String remove(long bno, RedirectAttributes redirectAttributes) {
//		log.info("bon: " + bno);
//		boardService.removeWithReplies(bno);
//		redirectAttributes.addFlashAttribute("msg", bno);
//		return "redirect:/board/list";
//	}
	
}