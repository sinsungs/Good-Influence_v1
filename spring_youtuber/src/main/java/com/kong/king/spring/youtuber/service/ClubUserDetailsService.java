package com.kong.king.spring.youtuber.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kong.king.spring.youtuber.dto.ClubAuthMemberDTO;
import com.kong.king.spring.youtuber.entity.ClubMember;
import com.kong.king.spring.youtuber.repository.ClubMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService{

	private final ClubMemberRepository clubMemberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("ClubUserDetailsService loadUserByUsername " + username);
		
		Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);
		
		if(result.isEmpty()) {
			throw new UsernameNotFoundException("check Email or Social");
		}
		
		ClubMember clubMember = result.get();
		
		log.info("-----------------------------");
		log.info(clubMember);
		
		ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
				clubMember.getEmail(),
				clubMember.getPassword(),
				clubMember.isFromSocial(),
				clubMember.getRoleSet().stream()
				.map(role -> new SimpleGrantedAuthority(
						"ROLE_" + role.name())).collect(Collectors.toSet())
				);
		clubAuthMember.setName(clubMember.getName());
		clubAuthMember.setFromSocial(clubMember.isFromSocial());
		
		return clubAuthMember;
	}

	
}
