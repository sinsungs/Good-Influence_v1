package team.java;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Post extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pno;
	
	private String title;
	private String content;	
	
    @OneToMany(mappedBy = "post")
    private List<PostYoutuber> postYoutubers = new ArrayList<>();

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL) // Post와 Payment는 1:1 관계
    private Payment payment;
}

