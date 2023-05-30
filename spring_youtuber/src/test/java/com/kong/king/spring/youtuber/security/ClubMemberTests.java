package com.kong.king.spring.youtuber.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kong.king.spring.youtuber.entity.ClubMember;
import com.kong.king.spring.youtuber.entity.ClubMemberRole;
import com.kong.king.spring.youtuber.repository.ClubMemberRepository;

@SpringBootTest
public class ClubMemberTests {

	@Autowired
	private ClubMemberRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void insertDummies() {
		// 1 - 80 User
		// 81 - 90 User , Manager
		// 91 - 100 User , Manager , Admin
		IntStream.rangeClosed(1, 100).forEach(i -> {
			
			ClubMember clubMember = ClubMember.builder()
					.email("user" + i + "@king.kong.com")
					.name("사용자" + i)
					.fromSocial(false)
					.roleSet(new HashSet<ClubMemberRole>())
					.password(passwordEncoder.encode("1111"))
					.build();
			
			clubMember.addMemberRole(ClubMemberRole.USER);
			if(i > 80) {
				clubMember.addMemberRole(ClubMemberRole.MANAGER);
			}
			if(i > 90) {
				clubMember.addMemberRole(ClubMemberRole.ADMIN);
			}
			repository.save(clubMember);
		
		});
			
	}
	
//	@Test
//	public void testRead() {
//		Optional<ClubMember> result = repository.findByEmail("user95@king.kong.com", false);
//		
//		ClubMember clubMember = result.get();
//		
//		System.out.println(clubMember);
//	}
}















