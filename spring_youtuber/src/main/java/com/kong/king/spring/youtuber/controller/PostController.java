package com.kong.king.spring.youtuber.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kong.king.spring.youtuber.dto.PostRequestDTO;
import com.kong.king.spring.youtuber.service.PostService;
import com.kong.king.spring.youtuber.service.PostYoutuberService;

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
    
//    @GetMapping("/list-academy")
//    public ResponseEntity<String> showRegisterAcademy() {
//        List<ExamineeAcademy> examineeAcademies = examineeAcademyRepository.findAll();
//
//        List<ExamineeAcademyDTO> dto = new ArrayList<>();
//        for(ExamineeAcademy examineeAcademy : examineeAcademies) {
//            dto.add(ExamineeAcademyDTO.of(examineeAcademy));
//        }
//
//        String response = dto.toString();
//        return ResponseEntity.ok(response);
//    }
//	
	
//	@GetMapping("/list")
//	public void list(PageRequestDTO pageRequestDTO, Model model) {
//		log.info("list..............." + pageRequestDTO);
//		
//		model.addAttribute("result", boardService.getList(pageRequestDTO));
//	}

    
}
    
    