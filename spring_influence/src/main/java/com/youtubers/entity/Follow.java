package com.youtubers.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 테이블 생성 효과
public class Follow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long follwid;
	
	@ManyToOne
	private User follower;
	
	@ManyToOne
	private User following;
}
