package com.youtubers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youtubers.Service.PostService;
import com.youtubers.Service.PostYoutuberService;
import com.youtubers.dto.PostRequestDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
	
    private final PostService postService;
    private final PostYoutuberService postyoutuberService;
    
    @PostMapping("/register")
    public  ResponseEntity<String> createPost(@RequestBody PostRequestDTO dto) {
    	
    	System.out.print(dto);
    	
    	Long pno = postService.createPost(dto);
    	
    	dto.setPno(pno);
    	
    	String response = postyoutuberService.createPost(dto);
        
//        String response = "success";
        return ResponseEntity.ok(response);
    }
    
	@GetMapping("/list")
	public ResponseEntity<String> listPost() {
		
		String response = postyoutuberService.getList();
		
        return ResponseEntity.ok(response);
	}
    
//    @GetMapping({"/read", "/modify"})
//    public ResponseEntity<String> Read(@RequestParam("pyno") Long pyno, Model model) {
//    	
//    	String response  = postyoutuberService.get(pyno);
////    	model.addAttribute("dto", youtuberDTO);
//    	
//    	return ResponseEntity.ok(response);
//  
//    }
    
//    
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
//	
//	
//    @PostMapping("/remove")
//    public String remove(@RequestParam("yno") Long yno){
//
//    	log.info("YNO : " + yno);
//
//    	youtuberService.remove(yno);
//
//    	return "redirect:/youtuber/list";
//    }
	

    
}
    
    