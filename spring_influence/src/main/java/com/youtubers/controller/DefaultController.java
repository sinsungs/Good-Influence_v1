package com.youtubers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtubers.Service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class DefaultController {

	@GetMapping("/")
	public String start() {
			
			String result = "success";
			
			return result;
		}
	
	@GetMapping("/meet")
	public String meet() {
			
			String result = "success";
			
			return result;
		}
	
	@GetMapping("/influencer")
	public String influencer() {
			
			String result = "success";
			
			return result;
		}
	
	@GetMapping("/list")
	public String list() {
			
			String result = "success";
			
			return result;
		}
	
	@GetMapping("/post")
	public String post() {
			
			String result = "success";
			
			return result;
		}
	
	@GetMapping("/rank")
	public String rank() {
			
			String result = "success";
			
			return result;
		}
	
}
