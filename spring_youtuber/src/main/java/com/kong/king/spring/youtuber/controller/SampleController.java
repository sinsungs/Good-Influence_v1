package com.kong.king.spring.youtuber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {

	@GetMapping("/all")
	public void exAll() {
		log.info("exAll...........");
	}
	
	@GetMapping("/member")
	public void exMember() {
		log.info("exMember..........");
	}
	
	
	@GetMapping("/admin")
	public void exAdmin() {
		log.info("exAdmin............");
	}
	
//	@GetMapping("/member")
//	public void exMember(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember) {
//		log.info("exMember..........");
//		log.info("----------------------");
//		log.info(clubAuthMember);
//	}
	
	
	
}