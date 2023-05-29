package com.kong.king.spring.youtuber.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kong.king.spring.youtuber.entity.Post;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
	
    private final PostService postService;
    
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(postId, post);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        boolean deleted = postService.deletePost(postId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PostMapping
    public ResponseEntity<PostYoutuber> addYouTuberToPost(@PathVariable Long postId, @RequestBody PostYoutuberDTO postYoutuberDTO) {
        postYoutuberDTO.setPostId(postId);
        PostYoutuber createdPostYoutuber = postYoutuberService.createPostYoutuber(postYoutuberDTO);
        if (createdPostYoutuber != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPostYoutuber);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add other CRUD functions as needed
//    @PostMapping("/{postId}/youtubers")
//    public ResponseEntity<Void> addYouTubersToPost(@PathVariable Long postId, @RequestBody List<Long> youtuberIds) {
//    	boolean added = postService.addYouTubersToPost(postId, youtuberIds);
//    	if (added) {
//    		return ResponseEntity.ok().build();
//    	} else {
//    		return ResponseEntity.notFound().build();
//    	}
//    }
}
    
    
}