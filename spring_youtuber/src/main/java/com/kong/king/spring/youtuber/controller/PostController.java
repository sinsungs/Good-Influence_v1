package com.kong.king.spring.youtuber.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kong.king.spring.youtuber.dto.PageRequestDTO;
import com.kong.king.spring.youtuber.dto.PostRequestDTO;
import com.kong.king.spring.youtuber.entity.Post;
import com.kong.king.spring.youtuber.entity.PostYoutuber;
import com.kong.king.spring.youtuber.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
	
    private final PostService postService;
    
    @PostMapping("/register")
    public ResponseEntity<PostYoutuber> createPost(@RequestBody PostRequestDTO dto) {
    	
    	System.out.print(dto);
    	
    	PostYoutuber postyoutuber = postService.createPost(dto);
        
        System.out.print(postyoutuber);
        
        return ResponseEntity.ok(postyoutuber);
    }
    
//	@GetMapping("/list")
//	public ResponseEntity<PostYoutuber> listPost() {
//		
//		PostYoutuber postyoutuber = PostService.getList();
//		
//        return ResponseEntity.ok(postyoutuber);
//	}
	
	
//	@GetMapping("/list")
//	public void list(PageRequestDTO pageRequestDTO, Model model) {
//		log.info("list..............." + pageRequestDTO);
//		
//		model.addAttribute("result", boardService.getList(pageRequestDTO));
//	}
//	
	
//	@PostMapping("/register")
//	public String createYoutuber(YoutuberDTO dto) {
//		
//		youtuberService.createYoutuber(dto);
//		
//		return "redirect:/youtuber/list";
//	}
	
    

//    @GetMapping("/{postId}")
//    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
//        Post post = postService.getPostById(postId);
//        if (post != null) {
//            return ResponseEntity.ok(post);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/{postId}")
//    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {
//        Post updatedPost = postService.updatePost(postId, post);
//        if (updatedPost != null) {
//            return ResponseEntity.ok(updatedPost);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{postId}")
//    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
//        boolean deleted = postService.deletePost(postId);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    
    
//    @PostMapping
//    public ResponseEntity<PostYoutuber> addYouTuberToPost(@PathVariable Long postId, @RequestBody PostYoutuberDTO postYoutuberDTO) {
//        postYoutuberDTO.setPostId(postId);
//        PostYoutuber createdPostYoutuber = postYoutuberService.createPostYoutuber(postYoutuberDTO);
//        if (createdPostYoutuber != null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdPostYoutuber);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PostMapping("/postyoutuber")
//    public ResponseEntity<Void> addYoutubersToPost(@RequestParam Long postId, @RequestBody List<Long> youtuberIds) {
//    	boolean added = postService.addYouTubersToPost(postId, youtuberIds);
//    	if (added) {
//    		return ResponseEntity.ok().build();
//    	} else {
//    		return ResponseEntity.notFound().build();
//    	}
//    }
    
    
}
    
    