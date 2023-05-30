package com.kong.king.spring.youtuber.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ClubMember {

	@Id
	private String email;
	private String password;
	private String name;
	private boolean fromSocial;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private Set<ClubMemberRole> roleSet = new HashSet<>();
	
	public void addMemberRole(ClubMemberRole clubMemberRole) {
		roleSet.add(clubMemberRole);
	}
	
}
