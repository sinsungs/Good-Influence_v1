package com.youtubers.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor 
//@ToString(exclude = {"writer"})

public class Influencer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ino;
	private String name;
	private String title;
	private String content;	
	private int likes;
	private String sns;
	private String category;
	
	private String imageUrl; // 이미지 파일의 경로나 URL을 저장할 필드
	
//    @OneToMany(mappedBy = "influencer")
//    private List<PostInfluencer> postInfluencers = new ArrayList<>();

    // 등록된 인플루언서 중 user 인증을 할 수 있다 
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // User 엔티티와의 관계, nullable = true로 설정
    private User user;
	
    
}

