package com.kong.king.spring.youtuber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/")
public class FirstController {

	@GetMapping("/home")
	public void home() {
		
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/logout")
	public void logout() {
		
	}
	
	@GetMapping("/guest")
	public String guest() {
		return "/guest/guest01";
	}
	
	@GetMapping("/manager")
	public String manager() {
		return "/manager/manager01";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "/admin/admin01";
	}
	
}