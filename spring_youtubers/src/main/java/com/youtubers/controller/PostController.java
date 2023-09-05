package com.youtubers.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youtubers.Service.PostInfluencerService;
import com.youtubers.Service.PostService;
import com.youtubers.dto.PostRequestDTO;
import com.youtubers.dto.PostResponseDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
	
    private final PostService postService;
    private final PostInfluencerService postInfluencerService;
    
    @PostMapping("/register")
    public  ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO dto, Authentication authentication) {
    	
    	System.out.println("정보" + authentication.getName());
    	
    	dto.setWriter(authentication.getName());
    	
    	Long pno = postService.createPost(dto);
    	
    	dto.setPno(pno);
    	
    	PostResponseDTO postResponseDTO = postInfluencerService.createInfluencerPost(dto);
//    	List<PostResponseDTO> postResponseDTO = postInfluencerService.createInfluencerPost(dto);
        
        return ResponseEntity.ok(postResponseDTO);
    }
    
    
    @GetMapping("/list")
    public ResponseEntity<List<PostResponseDTO>> listPost() {
    	
        List<PostResponseDTO> response = postInfluencerService.getList();
        
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
    
    