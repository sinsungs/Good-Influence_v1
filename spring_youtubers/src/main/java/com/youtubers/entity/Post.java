package com.youtubers.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Post extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pno;
	
	private String title;
	private String content;	
	
	private String imageUrl; // 이미지 파일의 경로나 URL을 저장할 필드
	
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostInfluencer> postYoutubers = new ArrayList<>();
    
    @ManyToOne 
    @JoinColumn(name = "user_id") // 외래키를 가리킬 컬럼 이름 지정
    private User user;
    
    
}

