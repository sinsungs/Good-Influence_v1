package com.youtubers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youtubers.Service.FollowService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FollowController {

	private FollowService followService;
	
	@PostMapping("/follow")
	public ResponseEntity<String> followUser(@RequestParam Long followerId, @RequestParam Long followingId) {
	    followService.followUser(followerId, followingId);
	    return ResponseEntity.ok("팔로우 성공");
	}
	
}
