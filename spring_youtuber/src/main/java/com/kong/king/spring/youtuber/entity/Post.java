package com.kong.king.spring.youtuber.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Post extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pno;
	
	private String title;
	private String content;	
	
	
//	@OneToMany(mappedBy = "post")
//	private List<PostYoutuber> postYoutubers;
	
    @OneToMany(mappedBy = "post")
    private List<PostYoutuber> postYoutubers = new ArrayList<>();
    
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<PostYoutuber> postYoutubers = new HashSet<>();
	
//	public void changeTitle(String title) {
//		this.title = title;
//	}
//	
//	public void changeContent(String content) {
//		this.content = content;
//	}
    
//    public void addPostYoutuber(PostYoutuber postYoutuber) {
//        postYoutubers.add(postYoutuber);
//        postYoutuber.setPost(this);
//    }

//    public void removePostYoutuber(PostYoutuber postYoutuber) {
//        postYoutubers.remove(postYoutuber);
//        postYoutuber.setPost(null);
//    }
    
    
}
